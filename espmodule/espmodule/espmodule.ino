/* 
  WiFiTelnetToSerial - Example Transparent UART to Telnet Server for esp8266

  Copyright (c) 2015 Hristo Gochkov. All rights reserved.
  This file is part of the ESP8266WiFi library for Arduino environment.
 
  This library is free software; you can redistribute it and/or
  modify it under the terms of the GNU Lesser General Public
  License as published by the Free Software Foundation; either
  version 2.1 of the License, or (at your option) any later version.

  This library is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public
  License along with this library; if not, write to the Free Software
  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
*/
#include <ESP8266WiFi.h>

//how many clients should be able to telnet to this ESP8266
#define MAX_SRV_CLIENTS 1
const char* ssid = "comhem_961AB0";
const char* password = "r8cj15k5";
char* command = "";

WiFiServer server(23);
WiFiClient serverClients[MAX_SRV_CLIENTS];

void setup() {
  Serial1.begin(115200);
  WiFi.begin(ssid, password);
  Serial1.print("\nConnecting to "); Serial1.println(ssid);
  uint8_t i = 0;
  while (WiFi.status() != WL_CONNECTED && i++ < 20) delay(500);
  if(i == 21){
    Serial1.print("Could not connect to"); Serial1.println(ssid);
    while(1) delay(500);
  }
  //start UART and the server
  Serial.begin(115200);
  server.begin();
  server.setNoDelay(true);

  Serial1.print("Ready! Use 'telnet ");
  Serial1.print(WiFi.localIP());
  Serial1.println(" 23' to connect");
}

void strAppend(char* origin, char c) {
  char newC[2];
  newC[0] = c;
  newC[1] = '\0';
  strcat(origin, newC);
}

void strClear(char* str) {
  memset(str, 0, sizeof str);  
}

void parseCommand(char* cmd, char* value) {
  Serial.write(cmd);
  Serial.write(" | ");
  Serial.write(value);
  Serial.write('\r');

  
  if (strcmp(cmd, "light") == 0) {
    // Code for turning light on / off based on passed args
    if (strcmp(value, "on") == 0) {
      Serial.write("ON");
      digitalWrite(LED_BUILTIN, LOW);
    }
    else if (strcmp(value, "off") == 0) {
      Serial.write("OFF");
      digitalWrite(LED_BUILTIN, HIGH);
    }
    else Serial.write("ELSE");
    Serial.write('\r');
  }
  else Serial.write("No command matched");
  
}

void handleConnectionData(char newChar) {
  //Serial.write("Got data: " + incoming.read());
  //Serial.write(incoming.read());
  if (newChar == '\r') {
    Serial.write(command);
    Serial.write('\r');

    char* cmd = strtok(command, " ");
    char* value = strtok(NULL, " ");
    parseCommand(cmd, value); // Re-add when implemented function
    strClear(command); // Reset stored command
  }
  else {
    //Serial.write(newChar);
    //command = strcat(command, newChar);
    strAppend(command, newChar);
  }
}

void loop() {
  uint8_t i;
  //check if there are any new clients
  if (server.hasClient()){
    for(i = 0; i < MAX_SRV_CLIENTS; i++){
      //find free/disconnected spot
      if (!serverClients[i] || !serverClients[i].connected()){
        if(serverClients[i]) serverClients[i].stop();
        serverClients[i] = server.available();
        Serial1.print("New client: "); Serial1.print(i);
        continue;
      }
    }
    //no free/disconnected spot so reject
    WiFiClient serverClient = server.available();
    serverClient.stop();
  }
  //check clients for data
  for(i = 0; i < MAX_SRV_CLIENTS; i++){
    if (serverClients[i] && serverClients[i].connected()){
      if(serverClients[i].available()){
        //get data from the telnet client and push it to the UART
        while(serverClients[i].available()) {
          //Serial.write(serverClients[i].read());
          handleConnectionData(serverClients[i].read());
        }
      }
    }
  }
  //check UART for data
  if(Serial.available()){
    size_t len = Serial.available();
    uint8_t sbuf[len];
    Serial.readBytes(sbuf, len);
    //push UART data to all connected telnet clients
    for(i = 0; i < MAX_SRV_CLIENTS; i++){
      if (serverClients[i] && serverClients[i].connected()){
        //Serial.println("Writing: " + *sbuf);
        serverClients[i].write(sbuf, len);
        delay(1);
      }
    }
  }
}



