package com.ktds.leinalee.articles.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.leinalee.articles.biz.ArticleBiz;
import com.ktds.leinalee.articles.vo.ArticleVO;
import com.ktds.leinalee.files.vo.FileVO;
import com.ktds.leinalee.util.DownloadUtil;

/**
 * Servlet implementation class ArticleDownload
 */
public class ArticleDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ArticleBiz articleBiz = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleDownload() {
        super();
        articleBiz = new ArticleBiz();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int articleId = Integer.parseInt(request.getParameter("articleId"));
		int fileId = Integer.parseInt(request.getParameter("fileId"));
		
		ArticleVO article = articleBiz.showDetail(articleId);
		List<FileVO> fileList = article.getFileList();
		
		if ( fileList != null ) {
			for (FileVO fileVo : fileList) {
				if (fileVo.getFileId() == fileId) {
					DownloadUtil downloadUtil = DownloadUtil.getInstance("D:\\");
					downloadUtil.download(request, response, fileVo.getFileName(), fileVo.getFileName());
				}
			}
		}
		
	}

}
