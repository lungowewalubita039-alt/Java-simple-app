# 1. Clean up nested/embedded .git folders that break tracking
Write-Host "Cleaning up broken nested Git folders..." -ForegroundColor Yellow
Get-ChildItem -Path . -Directory -Recurse -Depth 2 -Hidden -Filter ".git" | 
    Where-Object { $_.FullName -ne "$PWD\.git" } | 
    Remove-Item -Recurse -Force

# 2. Delete any local sub-project .gitignore files so only the root rule applies
Get-ChildItem -Path . -File -Recurse -Filter ".gitignore" | 
    Where-Object { $_.FullName -ne "$PWD\.gitignore" } | 
    Remove-Item -Force

# 3. Initialize the main root repository
Write-Host "Initializing root Git repository..." -ForegroundColor Yellow
git init

# 4. Tie to your specific GitHub identity
git config user.name "lungowewalubita039-alt"
git config user.email "lungowewalubita039@gmail.com"

# 5. Initialize remote BEFORE committing
Write-Host "Setting up remote origin..." -ForegroundColor Yellow
git remote remove origin 2>$null
git remote add origin "https://github.com/lungowewalubita039-alt/Java-simple-app.git"

# 6. Create a comprehensive root .gitignore that targets all subfolders
$gitignoreContent = @"
# Global Gradle and build ignores for all subfolders
**/build/
**/.gradle/
**/bin/
**/out/
build/
.gradle/
bin/
out/

# IDEs and caches
.idea/
*.iml
.vscode/
.settings/
.classpath
.project
__pycache__/

# OS files
.DS_Store
Thumbs.db
"@
Set-Content -Path ".gitignore" -Value $gitignoreContent -Force

# 7. Wipe cache completely, rebuild index, and stage files respecting the new ignore rules
Write-Host "Wiping cache and staging cleanly..." -ForegroundColor Yellow
git rm -r --cached . 2>$null
git add .

# 8. Commit with a timestamp
$timestamp = Get-Date -Format "yyyy-MM-dd HH:mm"
git commit -m "Automated Force Sync: $timestamp"

# 9. Force Overwrite GitHub
Write-Host "Force pushing to GitHub..." -ForegroundColor Yellow
git branch -M main
git push -u -f origin main

Write-Host "Success! Build folders are now ignored and GitHub has been updated." -ForegroundColor Green