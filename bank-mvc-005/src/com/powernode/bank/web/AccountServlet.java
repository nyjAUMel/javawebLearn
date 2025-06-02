package com.powernode.bank.web;

import com.powernode.bank.exception.AppException;
import com.powernode.bank.exception.NotSufficientFundsException;
import com.powernode.bank.exception.TransferException;
import com.powernode.bank.service.AccountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/bank/transfer")
public class AccountServlet extends HttpServlet {

    /**
     * 转账业务
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取数据
        String outAccount = req.getParameter("outAccount");
        String inAccount = req.getParameter("inAccount");
        double money = Double.parseDouble(req.getParameter("money"));

        AccountService accountService = new AccountService();
        try {
            // 调用转账业务
            accountService.transfer(outAccount, inAccount, money);
            // 展示处理结果(调用view)
            resp.sendRedirect(req.getContextPath() + "/success.jsp");
        } catch (NotSufficientFundsException e) {
            // 余额不足
            resp.sendRedirect("error.jsp");
        } catch (TransferException e) {
             // 转账异常
            resp.sendRedirect("error.jsp");
        } catch (AppException e) {
             // 业务异常
            resp.sendRedirect("error.jsp");
        }
    }
}
