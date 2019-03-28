#!/usr/bin/env groovy

pipeline {
    agent any
    options {
        timestamps()
    }
    tools { 
        maven 'Maven 3.3.9' 
        jdk 'JDK 8' 
    }
    stages {
        stage('Build') {
            steps {
                sh "mvn test" 
                sh "mvn sonar:sonar sonar:sonar -Dsonar.sourceEncoding=UTF-8 -Dsonar.analysis.mode=preview -Dsonar.github.repository=jjbustamante/scalable-web -Dsonar.verbose=true  -Dsonar.github.pullRequest=1 -Dsonar.github.login=bustamantejj@gmail.com -Dsonar.github.oauth=058bcd4688f316aaabae9b36c781da5c35fd0c88 -Dsonar.host.url=http://localhost:9000 -Dsonar.projectName=scalable-web -Dsonar.login=99394ca60c2fbf2ea768de94f4c4aaf8149a5dc1"
            }
        }
    }
}
