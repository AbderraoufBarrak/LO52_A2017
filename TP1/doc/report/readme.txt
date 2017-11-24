README for the Basic Report Template
By Joe Foley <foley@ru.is>
$Id: readme.txt 127 2013-02-14 17:19:21Z foley $
$URL: https://projects.cs.ru.is/svn/honnun2013/templates/LaTeX-BasicReport/readme.txt $

This simple report template demonstrates some of the features of latex.  It includes some of the elements of a standard report and the questions you would usually be answering.

This template uses biber/biblatex for citations instead of bibtex.  To
switch it back to bibtex, you will need to edit the line that says
\usepackage[backend=biber, bibencoding=utf8, style=ieee]{biblatex}
and change backend=bibtex

For more information and why the change was made, refer to Chapter 7 of http://afs.rnd.ru.is/project/htgaru/trunk/how-to-get-around-projects.pdf

The files/folders you will need are:
graphics/
example-basicreport.tex
IEEEtran.bst
references.bib
rureport.cls
custom.sty

When using this template with SVN, you will want to ignore:
*-blx.bib
*.aux
*.bak
*.bcf
*.bbl
*.bst
*.lo?
*.out
*.pdf
*.ps
*.run.xml
graphics/Thumbs.db
Thumbs.db
auto
