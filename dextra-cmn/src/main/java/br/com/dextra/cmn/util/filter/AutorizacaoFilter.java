package br.com.dextra.cmn.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author Mateus Bastos
 */
public class AutorizacaoFilter implements Filter
{
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		
	}

	public void init(FilterConfig config) throws ServletException 
	{
		
	}

	public void destroy() 
	{
		
	}
}