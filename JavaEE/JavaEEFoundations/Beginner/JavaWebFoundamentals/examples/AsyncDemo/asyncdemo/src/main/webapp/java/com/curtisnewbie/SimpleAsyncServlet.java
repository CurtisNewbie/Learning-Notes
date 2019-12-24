package com.curtisnewbie;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// async enabled for urlpattern "/async"
@WebServlet(urlPatterns = "/simple/async", asyncSupported = true)
public class SimpleAsyncServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log("AsyncServlet in doGet(). ThreadID:" + Thread.currentThread().getId());

        // get async context representing the whole asynchronous operation of this
        // request
        final AsyncContext asyncContext = req.startAsync();

        // set time out for the async context
        asyncContext.setTimeout(3000);

        // we can add listener to this context, and see when and which thread the
        // asynchronous operation is undertaken
        asyncContext.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent event) throws IOException {
                // when asynchronous operation complete or dispatched
                log("AsyncServlet asyncContext onComplete(). ThreadID:" + Thread.currentThread().getId());
            }

            @Override
            public void onError(AsyncEvent event) throws IOException {
                // when asynchronous operation has error/exceptions
                log("AsyncServlet asyncContext onError(). ThreadID:" + Thread.currentThread().getId());
            }

            @Override
            public void onTimeout(AsyncEvent event) throws IOException {
                // when asynchronous operation timeout
                log("AsyncServlet asyncContext onTimeout(). ThreadID:" + Thread.currentThread().getId());
            }

            @Override
            public void onStartAsync(AsyncEvent event) throws IOException {
                // when asynchronous operation starts
                log("AsyncServlet asyncContext onStartAsync(). ThreadID:" + Thread.currentThread().getId());
            }
        });

        // after registering listeners for the status of this asynchronous operation,
        // we need to specify the asynchronous operation
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                try {
                    log("AsyncServlet asyncContext start(). ThreadID:" + Thread.currentThread().getId());
                    // do something asynchronously
                    asyncContext.getResponse().getWriter().write("Processing in Thread:" + Thread.currentThread().getId());
                } catch (Exception e) {
                    log(e.toString());
                }

//                 when we complete the asynchronous operation, we must call complete() or
//                 dispatch() methods to tell the container that we are done, complete() is
//                 essentailly the same as the dispatch(), except that dispath() close the async
//                 operation and send the response and request to another servlet like normal.
                asyncContext.complete();
            }
        });

    }
}