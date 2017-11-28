# Whiteboard

Whiteboard is a POC student project for an Enterprise Learning Management System. The focus is more on Software Design and Architecture approaches discussed in [PoEAA book](https://www.martinfowler.com/books/eaa.html).

## Demo

- https://cloud.sankholin.com/whiteboard/

## Build

- Run `mvn` package to generate deployable `war` package

```
mvn clean package
ls -l target/
```

## Deploy to Heroku

- Create [Heroku account](https://www.heroku.com) 

- Install [heroku cli tool](https://devcenter.heroku.com/articles/heroku-cli)

    ```
    brew install heroku/brew/heroku
    ```

- Perform [WAR Deployment](https://devcenter.heroku.com/articles/war-deployment)

    ```
    cd whiteboard
    mvn clean package
    heroku login
    heroku plugins:install heroku-cli-deploy
    heroku apps:create whiteboardlms-dev
    heroku war:deploy target/whiteboard.war --app whiteboardlms-dev
    ```

- Then login to [Heroku dashboard](https://dashboard.heroku.com/apps) and check the additional `app` setting

## Performance and Static Analysis

- Look into the
 [performance](performance) directory

## About

This assignment work is done for _SWEN90007 Software Design and Architecture_ by Team 36 on continuous project assessment of 2017 SM2, The University of Melbourne.

You may wish to cite this work as follow.

LaTeX/BibTeX:

    @misc{whiteboard,
        author    = {Lin, San Kho and Zhang, Ruijing},
        title     = {Whiteboard - Enterprise LMS},
        year      = {2017},
        url       = {https://github.com/victorskl/whiteboard},
        urldate   = {yyyy-mm-dd}
    }