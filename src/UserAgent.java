public class UserAgent {

    private final String OS;
    private final String browser;

    public UserAgent(String userAgent) {
        if (!userAgent.contains("Mozilla/5.0")) {
            this.OS = "";
            this.browser = "";
        } else {
            if (userAgent.contains("X11")) {
                this.OS = "Linux";
            } else if (userAgent.contains("Windows")) {
                this.OS = "Windows";
            } else if (userAgent.contains("Macintosh")) {
                this.OS = "Mac OS";
            } else {
                this.OS = "Other OS";
            }
            if (userAgent.contains("Firefox/")) {
                this.browser = "Firefox";
            } else if (userAgent.contains("Edg/")) {
                this.browser = "Edge";
            } else if (userAgent.contains("Chrome/")) {
                this.browser = "Chrome";
            } else if (userAgent.contains("OPR/") || userAgent.contains("Presto/")) {
                this.browser = "Opera";
            } else {
                this.browser = "Other browser";
            }
        }
    }

    public String getOS() {
        return OS;
    }

    public String getBrowser() {
        return browser;
    }

    @Override
    public String toString() {
        return "UserAgent{" +
                "OS='" + OS + '\'' +
                ", browser='" + browser + '\'' +
                '}';
    }
}
