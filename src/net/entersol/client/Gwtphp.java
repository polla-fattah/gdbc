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


package net.entersol.client;

import net.entersol.gdbc.Gdbc;
import net.entersol.gdbc.ResultSet;
import net.entersol.gdbc.SqlException;
import net.entersol.gdbc.SqlStatment;
import net.entersol.iogate.IOGate;

import com.google.gwt.core.client.Duration;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gwtphp implements EntryPoint {
	private double start = Duration.currentTimeMillis();
	private double stop;
	private FlowPanel panel = new FlowPanel();
		private HTML mesaage = new HTML("<center><br><br><br>Hi There</center>");
		private Button button = new Button("Start");
		public void onModuleLoad(){
			panel.add(button);
			IOGate iog = new IOGate("http://localhost/gdbc/php/index.php", "polla", "tano");
			final Gdbc gdbc = new Gdbc(iog, Gdbc.DEFAULT_DB);

			
			final SqlStatment stmt = new SqlStatment(SqlStatment.INDIRECT) {

				@Override
				public void onQuerySuccess(ResultSet result){
					stop = Duration.currentTimeMillis();
					System.out.println(((stop - start)/1000) +" polla");
					
					mesaage.setHTML((((stop - start)/1000) +" polla<br><br>") + result.getJsonData().toString());
				}

				@Override
				public void onQueryFailure(SqlException sqlex) {
					System.out.println(sqlex.getMessage() + "Error Is Here 1 ");
				}
			};
			

			button.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					start = Duration.currentTimeMillis();
					
					stmt.setQuery("q1#col;Staff #col;#opr;#opr;#opr; idStaff #eql; 600 #orr; idStaff #eql; 123 #cpr;#cpr; #and; #opr;idStaff #gth; 300#cpr;#cpr;");
					gdbc.register(stmt);
					gdbc.submit();
					RootPanel.get().add(mesaage);
				}
			});
			RootPanel.get().add(panel);
		}
	}
/*
  This is test for IOGate
			IOGate iog = new IOGate("http://localhost/gwtphp/php/index.php");


			JsonObject data = IOGate.SYSTEM.createObject();
			data.put("first", "first one");
			data.put("second", 2222);
		
			JsonObject data1 = IOGate.SYSTEM.createObject();
			data1.put("first", "first one");
			data1.put("second", 1111);
			
			JsonObject data2 = IOGate.SYSTEM.createObject();
			data2.put("first", "first 434366");
			data2.put("second", 444);
			
			ios.get(data, this);
			ios.get(data1, this);
			ios.get(data2, this);

			data2 = IOGate.SYSTEM.createObject();
			data2.put("first", "3434 one");
			data2.put("second", 8888);
			
			ios.get(data2, this);

*/

