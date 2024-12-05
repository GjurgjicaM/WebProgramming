package mk.finki.ukim.mk.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.service.CategoryService;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import mk.finki.ukim.mk.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.servlet.IServletWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "eventListServlet", urlPatterns = "/servlet/events")
public class EventListServlet extends HttpServlet {
    private final EventService eventService;
    private final EventBookingService eventBookingService;
    private final SpringTemplateEngine springTemplateEngine;
    private final CategoryService categoryService;

    public EventListServlet(EventService eventService, EventBookingService eventBookingService,
                            SpringTemplateEngine springTemplateEngine, CategoryService categoryService) {
        this.eventService = eventService;
        this.eventBookingService = eventBookingService;
        this.springTemplateEngine = springTemplateEngine;
        this.categoryService = categoryService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JakartaServletWebApplication application = JakartaServletWebApplication.buildApplication(req.getServletContext());
        IServletWebExchange exchange = application.buildExchange(req, resp);
        WebContext context = new WebContext(exchange);

        context.setVariable("events", this.eventService.listAll());
        context.setVariable("categories", this.categoryService.findAll());

        springTemplateEngine.process("listEvents.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JakartaServletWebApplication application = JakartaServletWebApplication.buildApplication(req.getServletContext());
        IServletWebExchange exchange = application.buildExchange(req, resp);
        WebContext context = new WebContext(exchange);

        String category = req.getParameter("category");
        if (category != null && !category.isEmpty()) {
//            List<Event> searchList = this.eventService.searchByCategory(category);
//            context.setVariable("events", searchList);
            context.setVariable("categories", this.categoryService.findAll());
            springTemplateEngine.process("listEvents.html", context, resp.getWriter());
        }
        String search = req.getParameter("search");
        if (search != null && !search.isEmpty()) {
            List<Event> searchList = this.eventService.searchEvents(search);
            context.setVariable("events", searchList);
            context.setVariable("categories", this.categoryService.findAll());
            springTemplateEngine.process("listEvents.html", context, resp.getWriter());
        } else {
            String name = req.getParameter("event");
            String attName = req.getParameter("attName");
            String address = req.getRemoteAddr();
            int numTickets = Integer.parseInt(req.getParameter("numTickets"));
            EventBooking newBooking = eventBookingService.placeBooking(name, attName, address, numTickets);
            req.getSession().setAttribute("myBookings", eventBookingService.listAll());
//            req.getSession().setAttribute("myBookings", eventBookingService.placeBooking(name, attName, address, numTickets));
            resp.sendRedirect("/eventBooking");
        }
    }
}








