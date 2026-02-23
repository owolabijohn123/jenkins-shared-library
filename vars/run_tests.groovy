
def call() {
    sh "echo Running unit tests..."
    sh "npm install"
    sh "npm test"
}
