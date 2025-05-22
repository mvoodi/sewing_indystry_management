package kg.alatoo.sewing_industry_management.auth.security;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
public class IpBlacklistFilter implements Filter {

    private static final Set<String> BLACKLIST = Set.of("192.168.1.100");

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        String ip = req.getRemoteAddr();
        if (BLACKLIST.contains(ip)) {
            ((HttpServletResponse) res)
                    .sendError(HttpServletResponse.SC_FORBIDDEN, "IP blacklisted");
            return;
        }
        chain.doFilter(req, res);
    }
}

