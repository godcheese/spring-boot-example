CHCP 65001
@echo off
echo.author godcheese [godcheese@outlook.com]
set "CURRENT_DIR=%~dp0"
cd %CURRENT_DIR%
cd ..
echo.正在添加文件...
call git add .
set remarks=常规提交
set /p remarks=正在提交备注...，请填写备注（可空）:
call git commit -m "%remarks%"
echo.正在开始提交代码...
call git push origin master
echo.代码提交成功，正在关闭...
cd %CURRENT_DIR%