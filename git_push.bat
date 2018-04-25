CHCP 65001
@echo off
echo.author godcheese [godcheese@outlook.com]
set "CURRENT_DIR=%cd%"
cd %CURRENT_DIR%
echo.开始添加文件...
call git add .
set remark=Initial commit
set /p remark=开始提交备注...请填写备注（Initial commit）:
call git commit -m "%remark%"
echo.开始提交代码...
call git push origin master
echo.代码提交完毕，正在关闭...
cd %CURRENT_DIR%