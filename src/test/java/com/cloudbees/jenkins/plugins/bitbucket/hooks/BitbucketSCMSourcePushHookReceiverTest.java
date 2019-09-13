package com.cloudbees.jenkins.plugins.bitbucket.hooks;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.security.Principal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.ReadListener;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;
import jenkins.scm.api.SCMEvent;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.kohsuke.stapler.Ancestor;
import org.kohsuke.stapler.BindInterceptor;
import org.kohsuke.stapler.Stapler;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;
import org.kohsuke.stapler.WebApp;
import org.kohsuke.stapler.bind.BoundObjectTable;
import org.kohsuke.stapler.lang.Klass;

public class BitbucketSCMSourcePushHookReceiverTest {

    @Test
    public void notifyTest() throws IOException {
        BitbucketSCMSourcePushHookReceiver reciever = new BitbucketSCMSourcePushHookReceiver();
        DummyStaplerRequest req = new DummyStaplerRequest();
        String origin = SCMEvent.originOf(req);
        assertThat(SCMEvent.originOf(req), is("192.168.118.64 ⇒ http://jenkins.example.com:8080/bitbucket-scmsource-hook/notify"));
        String body = IOUtils.toString(req.getInputStream());
        assertThat(body, is(notNullValue()));
        String eventKey = req.getHeader("X-Event-Key");
        assertThat(eventKey, is("repo:refs_changed"));
//        alpha on August 6 2019. https://github.com/jenkinsci/atlassian-bitbucket-server-integration-plugin/tree/master/src/main/java/com/atlassian/bitbucket/jenkins/internal not in the market place?? https://community.atlassian.com/t5/Bitbucket-articles/Atlassian-supported-Jenkins-integration-for-Bitbucket-Server/ba-p/1148326
//http://stapler.kohsuke.org/getting-started.html Action methods might explain why this is called doNotify
//I think the donotify corresponds to the notify of the bitbucket-scmsource-hook/notify
        reciever.doNotify(req);
        assertThat(1, is(1));
//            @Rule
//    public JenkinsRule j = new JenkinsRule();
//    @Test
//    @Issue("JENKINS-42717")
//    public void shouldNotFailIfTheDefaultViewIsMissing() {
//        String viewName = "NonExistentView";
//        GlobalDefaultViewConfiguration c = new GlobalDefaultViewConfiguration();
//        StaplerRequest create = new MockStaplerRequestBuilder(j, "/configure").build();
    }

    class DummyStaplerRequest implements StaplerRequest {
        DummyStaplerRequest() throws IOException {

            this.buf = generateMultipartData();
            this.is = new ByteArrayInputStream(buf);
        }

        @Override
        public Stapler getStapler() {
            return null;
        }

        @Override
        public WebApp getWebApp() {
            return null;
        }

        @Override
        public String getRestOfPath() {
            return null;
        }

        @Override
        public String getOriginalRestOfPath() {
            return null;
        }

        @Override
        public ServletContext getServletContext() {
            return null;
        }

        @Override
        public String getRequestURIWithQueryString() {
            return null;
        }

        @Override
        public StringBuffer getRequestURLWithQueryString() {
            return null;
        }

        @Override
        public RequestDispatcher getView(Object o, String string) throws IOException {
            return null;
        }

        @Override
        public RequestDispatcher getView(Class type, String string) throws IOException {
            return null;
        }

        @Override
        public RequestDispatcher getView(Klass<?> klass, String string) throws IOException {
            return null;
        }

        @Override
        public String getRootPath() {
            return null;
        }

        @Override
        public String getReferer() {
            return null;
        }

        @Override
        public List<Ancestor> getAncestors() {
            return null;
        }

        @Override
        public Ancestor findAncestor(Class type) {
            return null;
        }

        @Override
        public <T> T findAncestorObject(Class<T> type) {
            return null;
        }

        @Override
        public Ancestor findAncestor(Object o) {
            return null;
        }

        @Override
        public boolean hasParameter(String string) {
            return false;
        }

        @Override
        public String getOriginalRequestURI() {
            return null;
        }

        @Override
        public boolean checkIfModified(long l, StaplerResponse sr) {
            return false;
        }

        @Override
        public boolean checkIfModified(Date date, StaplerResponse sr) {
            return false;
        }

        @Override
        public boolean checkIfModified(Calendar clndr, StaplerResponse sr) {
            return false;
        }

        @Override
        public boolean checkIfModified(long l, StaplerResponse sr, long l1) {
            return false;
        }

        @Override
        public void bindParameters(Object o) {

        }

        @Override
        public void bindParameters(Object o, String string) {

        }

        @Override
        public <T> List<T> bindParametersToList(Class<T> type, String string) {
            return null;
        }

        @Override
        public <T> T bindParameters(Class<T> type, String string) {
            return null;
        }

        @Override
        public <T> T bindParameters(Class<T> type, String string, int i) {
            return null;
        }

        @Override
        public <T> T bindJSON(Class<T> type, JSONObject jsono) {
            return null;
        }

        @Override
        public <T> T bindJSON(Type type, Class<T> type1, Object o) {
            return null;
        }

        @Override
        public void bindJSON(Object o, JSONObject jsono) {

        }

        @Override
        public <T> List<T> bindJSONToList(Class<T> type, Object o) {
            return null;
        }

        @Override
        public BindInterceptor getBindInterceptor() {
            return null;
        }

        @Override
        public BindInterceptor setBindListener(BindInterceptor bi) {
            return null;
        }

        @Override
        public BindInterceptor setBindInterceptpr(BindInterceptor bi) {
            return null;
        }

        @Override
        public BindInterceptor setBindInterceptor(BindInterceptor bi) {
            return null;
        }

        @Override
        public JSONObject getSubmittedForm() throws ServletException {
            return null;
        }

        @Override
        public FileItem getFileItem(String string) throws ServletException, IOException {
            return null;
        }

        @Override
        public boolean isJavaScriptProxyCall() {
            return false;
        }

        @Override
        public BoundObjectTable getBoundObjectTable() {
            return null;
        }

        @Override
        public String createJavaScriptProxy(Object o) {
            return null;
        }

        @Override
        public String getAuthType() {
            return null;
        }

        @Override
        public Cookie[] getCookies() {
            return null;
        }

        @Override
        public long getDateHeader(String string) {
            return 0;
        }

        @Override
        public String getHeader(String string) {
            return string.equals("X-Event-Key") ?  "repo:refs_changed" : null;
        }

        @Override
        public Enumeration<String> getHeaders(String string) {
            Set<String> myHeaders = new HashSet<>();
            myHeaders.add("X-Event-Key");
            return Collections.enumeration(myHeaders);
        }

        @Override
        public Enumeration<String> getHeaderNames() {
            return null;
        }

        @Override
        public int getIntHeader(String string) {
            return 0;
        }

        @Override
        public String getMethod() {
            return null;
        }

        @Override
        public String getPathInfo() {
            return null;
        }

        @Override
        public String getPathTranslated() {
            return null;
        }

        @Override
        public String getContextPath() {
            return null;
        }

        @Override
        public String getQueryString() {
            return null;
        }

        @Override
        public String getRemoteUser() {
            return null;
        }

        @Override
        public boolean isUserInRole(String string) {
            return false;
        }

        @Override
        public Principal getUserPrincipal() {
            return null;
        }

        @Override
        public String getRequestedSessionId() {
            return null;
        }

        @Override
        public String getRequestURI() {
            return "/bitbucket-scmsource-hook/notify";
        }

        @Override
        public StringBuffer getRequestURL() {
            return null;
        }

        @Override
        public String getServletPath() {
            return null;
        }

        @Override
        public HttpSession getSession(boolean bln) {
            return null;
        }

        @Override
        public HttpSession getSession() {
            return null;
        }

        @Override
        public String changeSessionId() {
            return null;
        }

        @Override
        public boolean isRequestedSessionIdValid() {
            return false;
        }

        @Override
        public boolean isRequestedSessionIdFromCookie() {
            return false;
        }

        @Override
        public boolean isRequestedSessionIdFromURL() {
            return false;
        }

        @Override
        public boolean isRequestedSessionIdFromUrl() {
            return false;
        }

        @Override
        public boolean authenticate(HttpServletResponse hsr) throws IOException, ServletException {
            return false;
        }

        @Override
        public void login(String string, String string1) throws ServletException {

        }

        @Override
        public void logout() throws ServletException {

        }

        @Override
        public Collection<Part> getParts() throws IOException, ServletException {
            return null;
        }

        @Override
        public Part getPart(String string) throws IOException, ServletException {
            return null;
        }

        @Override
        public <T extends HttpUpgradeHandler> T upgrade(Class<T> type) throws IOException, ServletException {
            return null;
        }

        @Override
        public Object getAttribute(String string) {
            return null;
        }

        @Override
        public Enumeration<String> getAttributeNames() {
            return null;
        }

        @Override
        public String getCharacterEncoding() {
            return null;
        }

        @Override
        public void setCharacterEncoding(String string) throws UnsupportedEncodingException {

        }

        @Override
        public int getContentLength() {
            return 0;
        }

        @Override
        public long getContentLengthLong() {
            return 0;
        }

        @Override
        public String getContentType() {
            return null;
        }

        final byte[] buf;
        final ByteArrayInputStream is;

        private byte[] generateMultipartData() throws IOException {
            MultipartEntityBuilder reqEntityBuilder = MultipartEntityBuilder.create();

//            reqEntityBuilder.setBoundary("mpboundary");
//            reqEntityBuilder.addBinaryBody("pomFile", new File("./pom.xml"), ContentType.TEXT_XML, "pom.xml");
            String body = "{\n"
                    + "  \"eventKey\": \"repo:refs_changed\",\n"
                    + "  \"date\": \"2019-09-11T16:43:13-0700\",\n"
                    + "  \"actor\": {\n"
                    + "    \"name\": \"jjayawee\",\n"
                    + "    \"emailAddress\": \"Janitha.Jayaweera@Tideworks.com\",\n"
                    + "    \"id\": 914,\n"
                    + "    \"displayName\": \"Janitha Jayaweera\",\n"
                    + "    \"active\": true,\n"
                    + "    \"slug\": \"jjayawee\",\n"
                    + "    \"type\": \"NORMAL\",\n"
                    + "    \"links\": {\n"
                    + "      \"self\": [\n"
                    + "        {\n"
                    + "          \"href\": \"https://bitbucket.tideworks.com/users/jjayawee\"\n"
                    + "        }\n"
                    + "      ]\n"
                    + "    }\n"
                    + "  },\n"
                    + "  \"repository\": {\n"
                    + "    \"slug\": \"hi-tide\",\n"
                    + "    \"id\": 298,\n"
                    + "    \"name\": \"hi-tide\",\n"
                    + "    \"scmId\": \"git\",\n"
                    + "    \"state\": \"AVAILABLE\",\n"
                    + "    \"statusMessage\": \"Available\",\n"
                    + "    \"forkable\": true,\n"
                    + "    \"project\": {\n"
                    + "      \"key\": \"DVOP\",\n"
                    + "      \"id\": 146,\n"
                    + "      \"name\": \"DevOps\",\n"
                    + "      \"public\": true,\n"
                    + "      \"type\": \"NORMAL\",\n"
                    + "      \"links\": {\n"
                    + "        \"self\": [\n"
                    + "          {\n"
                    + "            \"href\": \"https://bitbucket.tideworks.com/projects/DVOP\"\n"
                    + "          }\n"
                    + "        ]\n"
                    + "      }\n"
                    + "    },\n"
                    + "    \"public\": false,\n"
                    + "    \"links\": {\n"
                    + "      \"clone\": [\n"
                    + "        {\n"
                    + "          \"href\": \"https://bitbucket.tideworks.com/scm/dvop/hi-tide.git\",\n"
                    + "          \"name\": \"http\"\n"
                    + "        },\n"
                    + "        {\n"
                    + "          \"href\": \"ssh://git@bitbucket.tideworks.com:7999/dvop/hi-tide.git\",\n"
                    + "          \"name\": \"ssh\"\n"
                    + "        }\n"
                    + "      ],\n"
                    + "      \"self\": [\n"
                    + "        {\n"
                    + "          \"href\": \"https://bitbucket.tideworks.com/projects/DVOP/repos/hi-tide/browse\"\n"
                    + "        }\n"
                    + "      ]\n"
                    + "    }\n"
                    + "  },\n"
                    + "  \"changes\": [\n"
                    + "    {\n"
                    + "      \"ref\": {\n"
                    + "        \"id\": \"refs/heads/master\",\n"
                    + "        \"displayId\": \"master\",\n"
                    + "        \"type\": \"BRANCH\"\n"
                    + "      },\n"
                    + "      \"refId\": \"refs/heads/master\",\n"
                    + "      \"fromHash\": \"01ca9a69d620fb67da045c17bbce5550f0e0a9d8\",\n"
                    + "      \"toHash\": \"663e6926fcc7804f51a74ffe0bf46d97f5dfd59c\",\n"
                    + "      \"type\": \"UPDATE\"\n"
                    + "    }\n"
                    + "  ]\n"
                    + "}";
            reqEntityBuilder.addTextBody("body", body);
//            reqEntityBuilder.addTextBody("text2", "text2_val");
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                reqEntityBuilder.build().writeTo(outputStream);
                outputStream.flush();
                return outputStream.toByteArray();
            }
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            return new ServletInputStream() {
                @Override
                public int read() throws IOException {
                    return is.read();
                }

                @Override
                public int read(byte[] b) throws IOException {
                    return is.read(b);
                }

                @Override
                public int read(byte[] b, int off, int len) throws IOException {
                    return is.read(b, off, len);
                }

                @Override
                public boolean isFinished() {
                    return is.available() != 0;
                }

                @Override
                public boolean isReady() {
                    return true;
                }

                @Override
                public void setReadListener(ReadListener readListener) {
                    // ignored
                }
            };
        }
//        @Override
//        public ServletInputStream getInputStream() throws IOException {

//            InputStream is = new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8));
//            return (ServletInputStream) is;
//        }
        @Override
        public String getParameter(String string) {
            return null;
        }

        @Override
        public Enumeration<String> getParameterNames() {
            return null;
        }

        @Override
        public String[] getParameterValues(String string) {
            return null;
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            return null;
        }

        @Override
        public String getProtocol() {
            return null;
        }

        @Override
        public String getScheme() {
            return "http";
        }

        @Override
        public String getServerName() {
            return "jenkins.example.com";
        }

        @Override
        public int getServerPort() {
            return 8080;
        }

        @Override
        public BufferedReader getReader() throws IOException {
            return null;
        }

        @Override
        public String getRemoteAddr() {
            return "192.168.118.64";
        }

        @Override
        public String getRemoteHost() {
            return "192.168.118.64";
        }

        @Override
        public void setAttribute(String string, Object o) {

        }

        @Override
        public void removeAttribute(String string) {

        }

        @Override
        public Locale getLocale() {
            return null;
        }

        @Override
        public Enumeration<Locale> getLocales() {
            return null;
        }

        @Override
        public boolean isSecure() {
            return false;
        }

        @Override
        public RequestDispatcher getRequestDispatcher(String string) {
            return null;
        }

        @Override
        public String getRealPath(String string) {
            return null;
        }

        @Override
        public int getRemotePort() {
            return 0;
        }

        @Override
        public String getLocalName() {
            return null;
        }

        @Override
        public String getLocalAddr() {
            return null;
        }

        @Override
        public int getLocalPort() {
            return 8080;
        }

        @Override
        public AsyncContext startAsync() throws IllegalStateException {
            return null;
        }

        @Override
        public AsyncContext startAsync(ServletRequest sr, ServletResponse sr1) throws IllegalStateException {
            return null;
        }

        @Override
        public boolean isAsyncStarted() {
            return false;
        }

        @Override
        public boolean isAsyncSupported() {
            return false;
        }

        @Override
        public AsyncContext getAsyncContext() {
            return null;
        }

        @Override
        public DispatcherType getDispatcherType() {
            return null;
        }

    }

//    enum BitbucketWebhookEvent {
//
//        REPO_REF_CHANGE("repo:refs_changed"),
//        MIRROR_SYNCHRONIZED_EVENT("mirror:repo_synchronized"),
//        DIAGNOSTICS_PING_EVENT("diagnostics:ping"),
//        UNSUPPORTED("");
//
//        private final String eventId;
//
//        BitbucketWebhookEvent(String eventId) {
//            this.eventId = eventId;
//        }
//
//        public String getEventId() {
//            return eventId;
//        }
//
//        public static BitbucketWebhookEvent findByEventId(String eventId) {
//            for (BitbucketWebhookEvent event : values()) {
//                if (event.eventId.equalsIgnoreCase(eventId)) {
//                    return event;
//                }
//            }
//            return UNSUPPORTED;
//        }
//    }
}
