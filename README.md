I use this to get my issues to take home. As I don't have Internet at Home ... So with this I can just get all my issues at work and then be able to give me some direction
when working on my personal projects.

The Script generates issues in folder according to repo name and filenames based on titles.

So for example
<ul>
    <li>
        Projects
        <ul>
            <li>
                myprojecta
                <ul>
                    <li>
                        issue1 - username.txt
                    </li>
                    <li>
                        issue2 - username.txt
                    </li>
                    <li>
                        issue3 - username.txt
                    </li>
                    <li>
                        issue4 - username.txt
                    </li>
                </ul>
            </li>
            <li>
                myprojectb
                <ul>
                    <li>
                        issue1 - username.txt
                    </li>
                    <li>
                        issue2 - username.txt
                    </li>
                    <li>
                        issue3 - username.txt
                    </li>
                    <li>
                        issue4 - username.txt
                    </li>
                </ul>
            </li>
        </ul>
    
    </li>
</ul>

To get started just execute the jar with your username and api key:
java -jar Github_Issues.jar {yourusername} {apikey}

That's it!

Nice for offline use :D.

---
Makes use of https://github.com/nabeelmukhtar/github-java-sdk