<?php
/*
 * Copyright © 2011, Entersol Company. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright notice,
 *       this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright notice,
 *       this list of conditions and the following disclaimer in the documentation
 *       and/or other materials provided with the distribution.
 *     * Neither the names of entersol Company, enterhosts, gdbc
 *       nor the names of its contributors may be used to endorse or promote products derived
 *       from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT
 * SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *
 * Author: Polla A. Fattah
 */
/**
 * This class contains Constants as configurations for the system and resources for the entire system;
 * the behavior, type and location of services and resorces can be changed here.
 */
	class System {

		const DBMS = "mysql";							//Type of database used

		const DB_SERVER = "localhost";					//Path to the database server

		const DB_USER = "root";							//databse user for this application

		const DB_PASS = "tano";						//password for user

		const DB_NAME = "entersolapps";					//The main database for this application

		const AUTH_TABLE = "users";						//The table that used for authenticating
														//users for this Apps

		private static $appsdb = null;					//Genaral DB for the Application

		/**
		 * This function can be used to return system database and creates a connection to the default
	 	 * applications database
		 */
		static function getAppeDB(){
			if(self::$appsdb == null){
				try{
					self::$appsdb = new PDO(self::DBMS . ":host=" . self::DB_SERVER . ";dbname=" . self::DB_NAME, self::DB_USER, self::DB_PASS);
				}
				catch( PDOException $e ){
					IOGate::reportError("Error While trying to connect to the main database\n" . $e->getMessage());
				}
			}
			return self::$appsdb;
		}


		const ERROR_LOG_FILE = "errorlog.txt";			//Put file name that you want to log server occured errors
														//if you do not want log for errors leave it empty

		const PRODUCTION_MODE = true;

	}// end of class

?>
