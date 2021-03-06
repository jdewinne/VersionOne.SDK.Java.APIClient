package com.versionone.sdk.integration.tests;

import static org.junit.Assert.assertNotNull;

import java.net.MalformedURLException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.versionone.Oid;
import com.versionone.apiclient.Services;
import com.versionone.apiclient.V1Connector;
import com.versionone.apiclient.exceptions.V1Exception;
import com.versionone.apiclient.interfaces.IServices;


public class Connector {

	private static String _instanceUrl;
	private static String _username;
	private static String _password;
	private static String _accessToken;
	private static String _instanceUrlNTLM;
	private static Oid _projectId;
	private static String _use_oauth;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Running connector integration tests...");
		_instanceUrl = APIClientIntegrationTestSuiteIT.get_instanceUrl();
		_username = APIClientIntegrationTestSuiteIT.get_username();
		_password = APIClientIntegrationTestSuiteIT.get_password();
		_accessToken = APIClientIntegrationTestSuiteIT.get_accessToken();
		_projectId = APIClientIntegrationTestSuiteIT.get_projectId();
		_use_oauth = APIClientIntegrationTestSuiteIT.get_oauth();
	}
	
	@Test()
	public void ConnectorWithUsernameAndPassword() throws MalformedURLException, V1Exception {

		if (!_use_oauth.equals("true")){
			V1Connector conn = new V1Connector();
			V1Connector connector = conn.withInstanceUrl(_instanceUrl)
					.withUserAgentHeader("JavaSDKIntegrationTests", "1.0")
					.withUsernameAndPassword(_username, _password)
					.build();
			
			IServices services = new Services(connector);
			Oid oid = services.getLoggedIn();
			assertNotNull(oid);
		}
	}

	@Test
	public void ConnectorWithAccessToken() throws Exception {
		
		if (!_use_oauth.equals("true")){
			V1Connector conn = new V1Connector();
			V1Connector connector = conn.withInstanceUrl(_instanceUrl)
					.withUserAgentHeader("JavaSDKIntegrationTests", "1.0")
					.withAccessToken(_accessToken)
					.build();
			
			IServices services = new Services(connector);
			Oid oid = services.getLoggedIn();
			assertNotNull(oid);
		}
	}
}