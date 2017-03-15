#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""
- SocketServer:
	http://www.binarytides.com/python-socket-server-code-example/
- GPIO:
	http://raspberrypi.stackexchange.com/questions/22444/importerror-no-module-named-thread

"""

import socket
import sys # For exiting program
import os
import yaml # For loading config-file
from _thread import * # For handeling multiple client connections

# Server global variables
SERVER_RUNNING = True
CONFIG_FILE = "serverconfig.yaml"
SERVER_CONFIG = {}
ACTIVE_CONNECTIONS = 0

def loadConfigData():
	""" Tries to returns data from conf-file """
	global SERVER_CONFIG
	with open(CONFIG_FILE, 'r') as f:
		try: SERVER_CONFIG = yaml.load(f)
		except yaml.YAMLerror as e: print(e)

	if not SERVER_CONFIG: # if no config found --> exit
		print('Server config data could not be found --> exiting')
		sys.exit()

def increaseActiveConnections():
	global ACTIVE_CONNECTIONS
	ACTIVE_CONNECTIONS +=1

def decreaseActiveConnections():
	global ACTIVE_CONNECTIONS
	ACTIVE_CONNECTIONS -=1

def clearTerminal():
	os.system('clear')

def createSocketServer():
	# Create new socket
	socServer = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
	print('Socket created')

	# Try to configure server with data from conf-file
	host = SERVER_CONFIG['Network']['Host']
	port = SERVER_CONFIG['Network']['Port']
	try:
		socServer.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
		socServer.bind((host, port))
		print('Server bind complete')
	except socket.error as errorMsg:
		print('Failed to bind socketserver \nError Code:', errorMsg[0], '\nMessage:', errorMsg[1])
		sys.exit()

	# Start listening
	socServer.listen(SERVER_CONFIG['Server']['AcceptedFailedConntections'])
	print('Server now listening')

	return socServer

def clientThread(conn, addr):
	conn.send(b'HELLO!\n')

	def parseBytestr(bStr):
		""" decode bytestring and remove any newline chars """
		return bStr.decode('utf-8').strip()


	clientStillConnected = True
	while clientStillConnected:
		data = parseBytestr(conn.recv(1024))
		print('From client:', data)

		# Handle income data
		if data == 'exit': clientStillConnected = False
		elif data == 'clear': clearTerminal()


	conn.close()

def runServer(socketServer):
	while SERVER_RUNNING:
		# Retreive info on incomming connection
		conn, addr = socketServer.accept()
		print ('Connected with ' + addr[0] + ':' + str(addr[1]))
		
		# Start new thread for client
		start_new_thread(clientThread, (conn, addr))

	socketserver.close()


def main():
	# Load servers config from file
	loadConfigData()

	# Create and run new socket server
	socServer = createSocketServer()
	runServer(socServer)


if __name__ == '__main__':
	main()