/*
(MIT License)
Copyright (c) 2012 Johann du Toit
Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.johann.github;

import com.github.api.v2.schema.Issue;
import com.github.api.v2.schema.Repository;
import com.github.api.v2.services.GitHubServiceFactory;
import com.github.api.v2.services.IssueService;
import com.github.api.v2.services.RepositoryService;
import com.github.api.v2.services.auth.LoginTokenAuthentication;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Uses the Github Library (https://github.com/nabeelmukhtar/github-java-sdk) to get the issues still remaining from Github Repos
 * 
 * I use this to get my issues to take home. As I don't have Internet at Home ... So with this I can just get all my issues at work and then be able to give me some direction
 * when working on my personal projects.
 * 
 * @author Johann du Toit (exxonno@gmail.com)
 */
public class Issues {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args.length != 2) {
            System.out.println("Your Username and Api Key are Required. Read more at https://github.com/Johanndutoit/Github-Issues");
            System.exit(0);
        }
        
        /**
         * Create the Factory
         */
        GitHubServiceFactory issue_service = GitHubServiceFactory.newInstance();
        IssueService service = issue_service.createIssueService();
        RepositoryService repo_service = issue_service.createRepositoryService();
        
        /**
         * Authenticate to get access to all the repos
         */
        repo_service.setAuthentication(new LoginTokenAuthentication(args[0], args[1]));
        service.setAuthentication(new LoginTokenAuthentication(args[0], args[1]));

        /**
         * Create the Projects Dir where we will put the Repos in
         */
        File pdir = new File("Projects");
        if (!pdir.exists()) {
            pdir.mkdir();
        }

        /**
         * Loop each Repo and Create the Folder
         */
        for (Repository r : repo_service.getRepositories(args[0])) {

            /**
             * Create the Repo Dir
             */
            File dir = new File(pdir.getAbsolutePath() + "/" + r.getName());
            if (!dir.exists()) {
                dir.mkdir();
            }
            
            /**
             * Go through all the Issues from the Repo that are open
             */
            for (Issue issue : service.getIssues(args[0], r.getName(), Issue.State.OPEN)) {

                try {

                    /**
                     * Create a Writer and Write the Body
                     */
                    BufferedWriter writer = new BufferedWriter(new FileWriter(pdir.getAbsolutePath() + "/" + r.getName() + "/" + issue.getTitle() + " - " + issue.getUser() + ".txt"));
                    writer.write(issue.getBody() + "\n");
                    writer.flush();
                    writer.close();

                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                    ex.printStackTrace(); // Well you better do something here
                }
            }

        }
    }
}
