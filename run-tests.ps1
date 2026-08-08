# PowerShell script to compile and run tests without a global Gradle/Maven installation.
# It downloads the JUnit Standalone Console Launcher if not present, compiles source and test files, and executes the tests.

$JUNIT_VERSION = "1.10.0"
$JUNIT_JAR = "junit-platform-console-standalone-$JUNIT_VERSION.jar"
$JUNIT_URL = "https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/$JUNIT_VERSION/$JUNIT_JAR"

if (-not (Test-Path $JUNIT_JAR)) {
    Write-Host "Downloading JUnit Standalone Console Launcher..." -ForegroundColor Cyan
    Invoke-WebRequest -Uri $JUNIT_URL -OutFile $JUNIT_JAR
}

# Create build directory
if (-not (Test-Path "bin")) {
    New-Item -ItemType Directory -Path "bin" | Out-Null
} else {
    Remove-Item -Path "bin/*" -Recurse -Force -ErrorAction SilentlyContinue | Out-Null
}

Write-Host "Compiling Java classes..." -ForegroundColor Cyan
$sources = Get-ChildItem -Path "src/main/java" -Filter *.java -Recurse | ForEach-Object { $_.FullName }
$tests = Get-ChildItem -Path "src/test/java" -Filter *.java -Recurse | ForEach-Object { $_.FullName }

javac -d bin -cp $JUNIT_JAR ($sources + $tests)

if ($LASTEXITCODE -eq 0) {
    Write-Host "Compilation successful. Running tests..." -ForegroundColor Green
    java -jar $JUNIT_JAR execute --class-path bin --scan-class-path
} else {
    Write-Host "Compilation failed!" -ForegroundColor Red
    exit 1
}
