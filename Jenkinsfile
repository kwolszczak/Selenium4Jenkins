pipeline {
    agent any
    tools {
        jdk "openjdk17"
        maven "mvn from apache(default)"
    }


    stages {
        stage('Get Repo') {
            steps {
                echo '>>> Connecting to Git repo >>>>>'
                git branch: "${branch}", url: "https://github.com/kwolszczak/Selenium4Jenkins.git"
            }
        }
        stage('Configure YAML') {
            steps {
                script {
                    // Define the YAML file path relative to the WORKSPACE
                    def yamlFile = "${WORKSPACE}/src/test/resources/simple.yaml"

                    // Define the properties to be changed
                    def newEnv = "prod"
                    def newUrl = "https://www.example.com"
                    def newTimeout = "10"

                    // Install yq using jq
                    if (!fileExists('/usr/local/bin/yq')) {
                        echo "yq not found, installing..."
                        sh 'wget https://github.com/mikefarah/yq/releases/download/3.4.0/yq_linux_amd64 -O /usr/local/bin/yq'
                        sh 'chmod +x /usr/local/bin/yq'
                    }

                    // Change properties using yq
                    sh "yq eval-all 'select(fileIndex == 0) | .env = \"$newEnv\" | .url = \"$newUrl\" | .timeout = $newTimeout' -i $yamlFile"

                    // Display the updated YAML file
                    sh "cat $yamlFile"
                }
            }
        }

        stage('Build') {
            steps {
                  echo '>>> Build >>>>>'
                sh 'mvn clean compile' // for Linux
            }
        }

        stage('Test') {
            steps {
                echo '>>> Start test >>>>>'


                // Run Maven test with the selected environment
                sh "mvn test " // for Linux
            }
        }
    }
}
