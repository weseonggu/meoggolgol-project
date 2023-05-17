package com.soldesk.meoggolgol.MeoggolgolProject.mainpage;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JdbcCommandLineRunner implements CommandLineRunner {
	private final MainPageRepository mpr;
	@Override
	public void run(String... args) throws Exception {
		System.out.println(mpr.getAllFind());
	}
}
