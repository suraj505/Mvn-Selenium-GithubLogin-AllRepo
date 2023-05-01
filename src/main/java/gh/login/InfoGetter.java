package gh.login;

import java.util.List;

public class InfoGetter {
	
	public void acquireInfo(List<String> repoHrefs) {
		String following = repoHrefs.get(0);
		String followers = repoHrefs.get(1);
		List<String> personalRepos = repoHrefs.subList(2, repoHrefs.size());
		
		System.out.println(following);
		System.out.println(followers);
		System.out.println(personalRepos);
	}
	
}
