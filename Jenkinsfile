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
                sh "mvn sonar:sonar sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.projectName=scalable-web -Dsonar.login=99394ca60c2fbf2ea768de94f4c4aaf8149a5dc1"
            }
        }
    }
}
