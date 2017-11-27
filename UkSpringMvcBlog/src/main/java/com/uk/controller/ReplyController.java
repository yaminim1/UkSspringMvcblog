package com.uk.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uk.model.Article;
import com.uk.model.Reply;
import com.uk.service.ArticleService;
import com.uk.service.ReplyService;

@Controller
public class ReplyController {

	@Autowired
	ReplyService replyService;
	@Autowired
	ArticleService articleService;
	/**
	 * This method is used to save reply information in the Reply table
	 * 
	 * @param articleDTO
	 *            - contains the details related to the article
	 * @param bindingResultArticle
	 *            Binding Result
	 * @param replyDTO
	 *            - contains the details related to the reply
	 * @param bindingResult
	 *            -Binding Result Reply
	 * @param request
	 *            - HttpServlet request parameters
	 * @return Comments section
	 */

	@RequestMapping(value = "/save-reply", method = RequestMethod.POST)
	public String saveReply(@ModelAttribute Article article, BindingResult bindingResultArticle,
			@ModelAttribute Reply reply, BindingResult bindingResult, HttpServletRequest request, HttpSession session) {
		String userName = request.getRemoteUser();
		replyService.createReply(article.getId(), userName, reply);

		request.setAttribute("article", articleService.findById(article.getId()));
		request.setAttribute("replies", articleService.findById(article.getId()).getReply());

		request.setAttribute("articles", articleService.findAll());

		request.setAttribute("mode", "MODE_ARTICLES");
		request.setAttribute("All", "MODE_ALL");
		System.out.println("Successfully saved article");
		return "welcome";
	}

	/**
	 * This Method is called when the admin tries to reject an article comment
	 * 
	 * @param id
	 *            - This is the id of the article for which comments need to be
	 *            rejected
	 * @param request
	 *            - This has the Httpservlet request attributes
	 * @return - returns to the welcome screen after rejecting comment
	 */
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/reject-reply", method = RequestMethod.GET)
	public String rejectReply(@RequestParam int id, @RequestParam int replyId, HttpServletRequest request) {

		request.setAttribute("article", replyService.updateDelFlgReply(id, replyId));
		System.out.println("Successfully rejected reply Id:" + replyId);
		request.setAttribute("mode", "MODE_HOME");
		return "welcome";

	}

	/**
	 * This Method is called when the admin tries to verify/approve an article
	 * comment
	 * 
	 * @param id
	 *            - This is the id of the reply which need to be verified
	 * @param request-
	 *            This has the Httpservlet request attributes
	 * @return - returns to the welcome screen after approval of the comment
	 */
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/verify-reply", method = RequestMethod.GET)
	public String approveReply(@RequestParam int id, @RequestParam int replyId, HttpServletRequest request) {

		request.setAttribute("reply", replyService.updateVerifyReply(id, replyId));
		System.out.println("Successfully Approved reply Id:" + replyId);
		request.setAttribute("mode", "MODE_HOME");
		return "welcome";
	}
}
