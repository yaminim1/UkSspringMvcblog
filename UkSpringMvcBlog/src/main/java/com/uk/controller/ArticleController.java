package com.uk.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uk.model.Article;
import com.uk.service.ArticleService;
import com.uk.service.ReplyService;

@Controller
@RequestMapping("/")
public class ArticleController {

	@Autowired
	ArticleService articleService;

	@Autowired
	ReplyService replyService;
	/**
	 * This is the method called for Login
	 * 
	 * @param error=
	 *            If there is any error in login
	 * @param logout
	 *            = To show that user has logged out
	 * @return model
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		// log.info("Login");

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}
	/**
	 * This method is for displaying 403 access denied page when users with role
	 * other than admin/user.
	 * 
	 * @return Screen showing access denied
	 */
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();

			model.addObject("username", userDetail.getUsername());

		}

		model.setViewName("403");
		return model;

	}
	/**
	 * This method is to call the welcome screen
	 * 
	 * @param request
	 *            Httpservlet Request
	 * @return welcome Screen
	 */

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_HOME");
		return "welcome";
	}

	/**
	 * This method is used to display screen to create new article
	 * 
	 * @param request
	 *            - Http Servlet Request parameters
	 * @return - The screen to enter article title and description
	 */
	@RequestMapping(value = "/new-article", method = RequestMethod.GET)
	public String create(HttpServletRequest request) {

		request.setAttribute("mode", "MODE_NEW");
		return "welcome";
	}
	/**
	 * This method is called when All Articles tab is clicked to view all the
	 * articles.
	 * 
	 * @param page
	 *            - page Number
	 * @param size
	 *            - The number of articles that need to be shown on a single
	 *            page
	 * @param request
	 *            - HttpServlet request contains all the request parameters.
	 * @param session-
	 *            HttpSession contains all the session information
	 * @return- To welcome screen to display all the articles
	 */
	@RequestMapping(value = "/all-articles", method = RequestMethod.GET)
	public String allArticles(HttpServletRequest request) {

		request.setAttribute("articleSize", articleService.findAll().size());
		request.setAttribute("articles", articleService.findAll());
		request.setAttribute("mode", "MODE_ARTICLES");
		request.setAttribute("All", "MODE_ALL");
		// System.out.println("Displayed all articles" + size);
		return "welcome";
	}
	
	/**
	 * This method is used to update the article with a given article Id
	 * 
	 * @param id
	 *            - Article Id
	 * @param request
	 *            - Http Servlet Request Parameters
	 * @return- return the page to update the article Id.
	 */

	@RequestMapping(value = "/update-article", method = RequestMethod.GET)
	public String updateTask(@RequestParam int id, HttpServletRequest request) {

		request.setAttribute("article", articleService.findById(id));
		request.setAttribute("mode", "MODE_UPDATE");
		System.out.println("Successfully updated task with article id " + id);
		return "welcome";
	}
	/**
	 * This method is used to delete an article created by the user
	 * 
	 * @param id
	 *            - Article Id which needs to be deleted
	 * @param request
	 *            - Http Servlet Request parameters.
	 * @param session
	 *            - Contains session information
	 * @return - return to screen showing all articles
	 */
	@RequestMapping(value = "/delete-article", method = RequestMethod.GET)
	public String deleteArticle(@RequestParam int id, HttpServletRequest request, HttpSession session) {

		int page = 0;
		articleService.removeArticle(id);
		session.setAttribute("page", page);
		request.setAttribute("articles", articleService.findAll());
		request.setAttribute("articleSize", articleService.findAll().size());
		request.setAttribute("mode", "MODE_ARTICLES");
		request.setAttribute("All", "MODE_ALL");
		System.out.println("Deleted Article Successfully{}" + id);
		return "welcome";
	}
	/**
	 * This method is used to search by Article Title
	 * 
	 * @param title
	 *            Article Title to be used for search
	 * @param request-
	 *            Http Servlet request parameters
	 * @return - The screen showing all the articles matching the search
	 *         criteria
	 */
	@RequestMapping(value = "/find-article", method = RequestMethod.GET)
	public String findArticle(@RequestParam String title, HttpServletRequest request) {
		System.out.println("Find Article");
		request.setAttribute("articles", articleService.findByTitle(title));

		request.setAttribute("mode", "MODE_ARTICLES");
		request.setAttribute("articleSize", articleService.findByTitle(title).size());
		System.out.println("Found Article" + articleService.findByTitle(title).size());
		return "welcome";
	}
	/**
	 * This method is used to save the article
	 * 
	 * @param article
	 *            - contains the details related to the article
	 * @param bindingResult
	 *            - Binding Result
	 * @param request
	 *            - HttpServlet Request
	 * @param session
	 *            - Contains session information
	 * @return - After saving the article it displays all the articles.
	 */

	@RequestMapping(value = "/save-article", method = RequestMethod.POST)
	public String saveArticle(@ModelAttribute Article article, BindingResult bindingResult, HttpServletRequest request,
			HttpSession session) {
		String userName = request.getRemoteUser();
		System.out.println("saved article id"+article.getId());
		
		System.out.println("created by"+article.getCreatedBy());
		if(article.getId() !=null){
		articleService.editArticle(userName, article);
		}
		else
		articleService.create(userName, article);
		int page = 0;
		session.setAttribute("page", page);
		request.setAttribute("articles", articleService.findAll());

		request.setAttribute("mode", "MODE_ARTICLES");
		request.setAttribute("All", "MODE_ALL");
		System.out.println("Successfully saved article");
		return "welcome";
	}
	/**
	 * This method lets us post or view existing comments on a particular
	 * article
	 * 
	 * @param id
	 *            - Article id for which comments need to be retrieved,
	 * @param request
	 *            HttpServlet request parameters
	 * @return The Screen showing the article and the comments for that article
	 */

	@RequestMapping(value = "/read-more", method = RequestMethod.GET)
	public String comments(@RequestParam int id, HttpServletRequest request) {

		request.setAttribute("article", articleService.findById(id));
		request.setAttribute("replies", replyService.getComments(id));
		System.out.println("Displayed comment section");
		return "article";
	}
	
	/**
	 * This Method is used to view all the verified articles
	 * 
	 * @param request
	 *            - Http Servlet Request parameters
	 * @return - return screen displaying all verified articles
	 */
	@RequestMapping(value = "/all-articles-verify", method = RequestMethod.GET)
	public String allArticlesUser(HttpServletRequest request) {
		request.setAttribute("articleSize", articleService.findAllByVerify().size());
		request.setAttribute("articles", articleService.findAllByVerify());
		request.setAttribute("mode", "MODE_ARTICLES");
		return "welcome";
	}

	/**
	 * This Method is used to show all the articles created by the logged in
	 * user
	 * 
	 * @param request-
	 *            Http Servlet Request parameters
	 * @return screen display all the articles created by the logged in iser
	 */

	@RequestMapping(value = "/my-articles", method = RequestMethod.GET)
	public String userArticles(HttpServletRequest request) {
		request.setAttribute("articles", articleService.findAllByCreatedBy(request.getRemoteUser()));
		request.setAttribute("mode", "MODE_ARTICLES");
		return "welcome";
	}

	/**
	 * This Method is called when the admin tries to verify/approve an article
	 * 
	 * @param id
	 *            - This is the id of the article which need to be verified
	 * @param request-
	 *            This has the Httpservlet request attributes
	 * @return - returns to the welcome screen after approval of the article
	 */

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/verify-article", method = RequestMethod.GET)
	public String verifyArticle(@RequestParam int id, HttpServletRequest request) {

		request.setAttribute("article", articleService.updateVerify(id));
		request.setAttribute("mode", "MODE_HOME");
		System.out.println("Successfully Updated Reply Flag {}: " + id);
		return "welcome";
	}

	/**
	 * This Method is called when the admin tries to reject an article
	 * 
	 * @param id
	 *            - This is the id of the article which need to be verified
	 * @param request
	 *            - This has the Httpservlet request attributes
	 * @return - returns to the welcome screen after approval of the article
	 */

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/reject-article", method = RequestMethod.GET)
	public String rejectArticle(@RequestParam int id, HttpServletRequest request) {

		request.setAttribute("article", articleService.updateDelFlg(id));
		System.out.println("Successfully rejected article Id:" + id);
		request.setAttribute("mode", "MODE_HOME");
		return "welcome";
	}
}
