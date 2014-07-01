package com;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OauthMockServlet
 */
@WebServlet("/facebook/me")
public class FacebookMockServlet extends HttpServlet {

    private static final String ACCESS_TOKEN = "access_token";
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FacebookMockServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
        IOException {

        final String access_token = request.getParameter(ACCESS_TOKEN);
        if (access_token == null) {
            response.getWriter().write("access token must be provided");
        } else {
            response.setContentType("application/json;");
            final String validFacebookMeResponse = getValidFacebookMeResponse();
            response.getWriter().write(validFacebookMeResponse);
            System.out.println("Sending Response: " + validFacebookMeResponse);
        }
        response.flushBuffer();
    }

    String getValidFacebookMeResponse() {
        final String result =
            "{\"id\": \"719883168076083\"," + "\"email\": \"test@dummy.com\"," + "\"first_name\": \"xyz\","
                + "\"gender\": \"male\"," + "\"last_name\": \"xyz\","
                + "\"link\": \"https://www.facebook.com/app_scoped_user_id/719883168076083/\"," + "\"locale\": \"en_US\","
                + "\"name\": \"xyz\"," + "\"timezone\": 5.5," + "\"updated_time\": \"2012-10-11T03:12:22+0000\","
                + "\"verified\": true}";

        return result;
    }
    
    String getValidFacebookMeResponseBlocking() {
        final String result =
            "{\"id\": \"719883168076083\"," + "\"email\": \"test@dummy.com\"," + "\"first_name\": \"xyz\","
                + "\"gender\": \"male\"," + "\"last_name\": \"xyz\","
                + "\"link\": \"https://www.facebook.com/app_scoped_user_id/719883168076083/\"," + "\"locale\": \"en_US\","
                + "\"name\": \"xyz\"," + "\"timezone\": 5.5," + "\"updated_time\": \"2012-10-11T03:12:22+0000\","
                + "\"verified\": true}";

        try {
			Thread.sleep(1000);
		} catch (InterruptedException ignore) {		}
        
        return result;
    }
}
