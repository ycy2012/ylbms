package com.ylbms.test;

import java.util.Date;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EhcacheTest {

	private final static Logger log = LoggerFactory.getLogger(EhcacheTest.class);
	public final static CacheManager cm = CacheManager.getInstance();
	public final static Cache wc = cm.getCache("readCache");
	
	static {
		wc.put(new Element("abc0", "123"));
		wc.put(new Element("abc1", "123"));
		wc.put(new Element("abc2", "123"));
		wc.put(new Element("abc3", "123"));
		wc.put(new Element("abc4", "123"));
		wc.put(new Element("abc5", "123"));
		wc.put(new Element("abc6", "123"));
		wc.put(new Element("abc7", "123"));
		wc.put(new Element("abc8", "123"));
		wc.put(new Element("abc9", "123"));
	}

	public final static int MOD = 10240;

	public static void main(String[] args) throws InterruptedException {
		final Thread p, np, g;

		p = new Thread(new Putter(true), "not-in-put");
		p.setDaemon(true);
		p.start();

		np = new Thread(new Putter(false), "not-in-np");
		np.setDaemon(true);
		np.start();

		g = new Thread(new Getter(), "gtr");
		g.setDaemon(true);
		g.start();

		System.out.println("Starting at " + new Date());
		p.join();
		np.join();
		g.join();
	}

	static class Putter implements Runnable {
		private boolean notInPut = false;

		public Putter(boolean notInPut) {
			this.notInPut = notInPut;
		}

		public void run() {
			try {
				for (;;) {
					for (int i = 0; i < MOD; ++i) {
						String k = "abc" + i;
						if (wc.isKeyInCache(k) || notInPut) {
							wc.put(new Element(k, "123"));
						} else {
							log.info("The key {} isn't putted", k);
						}
					}
					Thread.sleep(10000L);
				}
			} catch (InterruptedException e) {
				log.info("exit");
				return;
			}
		}
	}

	static class Getter implements Runnable {
		public void run() {
			try {
				for (;;) {
					for (int i = 0; i < MOD / 2; ++i) {
						int r = (int) (MOD * Math.random());
						String k = r < i ? "abc" + r : "abc";
						if (wc.isKeyInCache(k)) {
							log.info("The element {} hitted in readCache",
									i % 2 == 0 ? wc.getQuiet(k) : wc.get(k));
						}
					}
					Thread.sleep(3000L);
				}
			} catch (InterruptedException e) {
				log.info("exit");
				return;
			}
		}
	}
}