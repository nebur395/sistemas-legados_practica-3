package servlets;


import connection.MenaData;
import models.Program;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;

public class FilterByTape extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public FilterByTape() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MenaData menaData = new MenaData();
        menaData.getWindowPosition();
        String tape= request.getHeader("tape");
        Iterator<Program> iter = menaData.searchByTape(tape).iterator();
        JSONArray array = constructResponse(iter);
        JSONObject json = new JSONObject();
        json.element("programs", array);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(json.toString());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private JSONArray constructResponse (Iterator<Program> itr) {
        JSONArray ja = new JSONArray();
        while(itr.hasNext()){
            JSONObject prog = JSONObject.fromObject(itr.next().serialize());
            ja.add(prog);
        }
        return ja;
    }

}

