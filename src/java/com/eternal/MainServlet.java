package com.eternal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = {"*.html"})
public final class MainServlet extends HttpServlet {
    public static final String INTERVAL = "interval";
    public static final String DELTA = "delta";
    public HttpSession session;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session = req.getSession(false);
        session = session != null ? session : req.getSession();
        req.getRequestDispatcher("/main.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String interval = req.getParameter(INTERVAL);
        String delta = req.getParameter(DELTA);
        validateInterval(interval);
        validateDelta(delta);
        if (session.getAttribute(DELTA) != null && session.getAttribute(INTERVAL) != null)
            createFunction(interval, delta);

        resp.sendRedirect("/");
    }

    public void createFunction(String interval, String delta) {
        double minX = Double.parseDouble(interval.split("-")[0]);
        double maxX = Double.parseDouble(interval.split("-")[1]);
        double deltaX = Double.parseDouble(delta);
        Function function = new Function(minX, maxX, deltaX);
        session.setAttribute("function", function);
    }

    private void validateInterval(String interval) {
        interval = interval.replaceAll("\\s*", "");
        Pattern intervalPattern = Pattern.compile("\\d+\\.?\\d*-\\d+\\.?\\d*");
        Matcher matcher = intervalPattern.matcher(interval);
        if (matcher.matches()) {
            session.setAttribute(INTERVAL, interval);
            session.setAttribute("intervalError", null);
        } else {
            session.setAttribute(INTERVAL, null);
            session.setAttribute("intervalError", "Проверьте правильность ввода. Введите интервал в форме число-число");
        }
    }

    private void validateDelta(String delta) {
        delta = delta.replaceAll("\\s*", "");
        Pattern deltaPattern = Pattern.compile("\\d+\\.?\\d+");
        Matcher deltaMatcher = deltaPattern.matcher(delta);
        if (deltaMatcher.matches() && Double.parseDouble(delta) >= 0) {
            session.setAttribute(DELTA, delta);
            session.setAttribute("deltaError", null);
        } else {
            session.setAttribute(DELTA, null);
            session.setAttribute("deltaError", " Проверьте правильность ввода. Шаг должен быть больше 0");
        }
    }

    public HttpSession getSession() {
        return session;
    }
}
