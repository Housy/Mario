@echo off

set a=1

setlocal EnableDelayedExpansion

for %%n in (*.png) do (

set /A a+=1

ren "%%n" "mario!a!.png"

)