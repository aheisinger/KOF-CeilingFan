/**
 *  King Of Fans Zigbee Fan Controller - Light Child Device
 *
 *  Copyright 2017 Stephan Hackett
 *  in collaboration with Ranga Pedamallu, Dale Coffing
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 */
 def version() {return "ver 0.2.1.20170501b"}
/*
    b- changed light TurningON/OFF icon to light_blue 
    a- changed light ON icon from light_blue to lightH for Stephan  
 05/01 added version tile for iOS child device view, light icon ON with blue rays
 04/30a move Stephack latest changes over in a copy/paste; change namespace
 04/29 larger matching icon; used URL shortcut https://cdn.rawgit.com/ and located to /resources/images/
 04/26 moved icons to KOF repo and renamed for final release
 04/20 modified version tile 
 04/19 added version tile to help in troubleshooting with users
 2017 Year
*/
metadata {
	definition (name: "KOF Zigbee Fan Controller - Light Child Device", namespace: "dcoffing", author: "Stephan Hackett") {
		capability "Actuator"
        capability "Switch"
        capability "Switch Level"
        capability "Light"
        capability "Sensor" 
   }

	tiles(scale: 2) { 		
        multiAttributeTile(name:"switch", type: "lighting", width: 6, height: 4, canChangeIcon: true) {
    		tileAttribute ("switch", key: "PRIMARY_CONTROL") {
        		attributeState "off", label:"off", action: "on", icon: getIcon()+"light_grey.png", backgroundColor: "#ffffff", nextState: "turningOn"
				attributeState "on", label: "on", action: "off", icon: getIcon()+"lightH.png", backgroundColor: "#00A0DC", nextState: "turningOff"
                attributeState "turningOn", label:"TURNING ON", action: "on", icon: getIcon()+"light_blue.png", backgroundColor: "#2179b8", nextState: "turningOn"
            	attributeState "turningOff", label:"TURNING OFF", action:"off", icon: getIcon()+"light_blue.png", backgroundColor:"#2179b8", nextState: "turningOff"
        	}    	
    		tileAttribute ("device.level", key: "SLIDER_CONTROL") {
        		attributeState "level", action: "setLevel"
    		}  
		}	
 		valueTile("version", "version", width: 6, height: 2) {
          	state "version", label:"Light Child\n" + version()
		}                
    	main(["switch"])        
		details(["switch", "version"])    
    }	
}

def getIcon() {
	return "https://cdn.rawgit.com/dcoffing/KOF-CeilingFan/master/resources/images/"
}

def on() {
	parent.lightOn()
	sendEvent(name: "switch", value: "on")
}

def off() {
	parent.lightOff()
    sendEvent(name: "switch", value: "off")
}

def setLevel(val) {
	parent.lightLevel(val)
    sendEvent(name: "level", value: val)
}