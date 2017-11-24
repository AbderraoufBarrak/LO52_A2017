%% $Header$
%% Indiana State Purdue Fort Wayne Paper Template
%% Initial version by Joe Foley <foley AT RU.IS>
%% Maintained by David S. Cochran <cochrand AT ipfw.edu>
%% Created on 2017-03-30
%% This code is distributed under the Perl Artistic License
%% http://language.per.com/misc/Artistic.html
%% Some code is derived from the IEEE LaTeX class, RU ad-hoc
%% wireless project labeling system, and RU CS M.Sc. Thesis and Report template

%% --------------- Identification ----------------------------
\newcommand{\fileversion@ipfwpaper}{v0.1}
\newcommand{\filedate@ipfwpaper}{2017/03/30}
\newcommand{\fileauthor@ipfwpaper}{David S. Cochran, cochrand@ipfw.edu}
\ProvidesClass{ipfwpaper}[\filedate@ipfwpaper by \fileauthor@ipfwpaper]

\NeedsTeXFormat{LaTeX2e}
\typeout{Indiana State Purdue Fort Wayne Paper Template}

%% ---------------- Initial Code -----------------------------
\RequirePackage{ifthen}
% This package allows you to create conditionals on booleans
\RequirePackage{ifdraft}
\newboolean{svn}

% Better handling of arguments, particularly for defaults
\RequirePackage{xargs}

%% ---------------- Declaration of Options--------------------
%% Remember that we have \AtEndOfClass{},\AtEndOfPackage{}
%%     \AtBeginDocument{}, \AtEndDocument{}
%% If you want execution conditional on file existence
%%     \IfFileExists{filename}{true}{false}
%%     \InputIfFileExists{filename}{true}{false}
%% For error reporting
%%     \ClassError{class-name}{error-text}{help-text}
%%     \PackageError{class-name}{error-text}{help-text}
%\DeclareOption{rulogotitle}{
% % TODO: finish this.  RU logo on the title page
% 
% }
%% otherwise, just a generic title page

\DeclareOption{svn}{
  \setboolean{svn}{true}
}

%% now we pass the remaining unused options to our parent class
\DeclareOption*{\PassOptionsToClass{\CurrentOption}{article}}
%% ---------------- Execution of Options -----------------------------
\ProcessOptions \relax % this stops searching for a * in
                       % \ProcessOtions*

%% Alternative way to subclass.  Disable the other option processing
%% if you enable it.  This will dump all options onto the parent class
%\ProcessOptions\LoadClassWithOptions{article}
%% ---------------- Package Loading -----------------------------
\LoadClass{article}  % our parent class.  Article is a little easier
                     % to work with than the report class.
\RequirePackage[T1]{fontenc}
\RequirePackage[utf8]{inputenc}  % allows UTF encoding, needed for Icelandic characters
\RequirePackage[margin=2cm,a4paper]{geometry}
%% Some slighly more sensible margins on an A4 page
%% This can be tuned for your given report

% Some useful fonts, particularly for listings
\RequirePackage{lipsum}
\RequirePackage{courier}

% Need this for the tables used in books
% \toprule, \midrule, \botrule
\RequirePackage{booktabs}


\ifthenelse{\boolean{svn}}{
        % switching to the svn-multi package which is much smarter
  %\RequirePackage[today,fancyhdr]{svninfo} 
  % grab the SVN revision information from a \svninfo $Id: ipfwpaper.cls 146 2016-08-22 16:02:15Z foley $
  %% today option sets the current day to the latest SVN date
  %% Remember that you will need to put a \svninfo and \svnKeyword
  %% into your document or it will only grab the revision information
  %% of the class file!  Also, you will need to set the svn:keywords properties.
  %\svnInfo $Id: ipfwpaper.cls 146 2016-08-22 16:02:15Z foley $
  %\svnKeyword $HeadURL: https://repository.cs.ru.is/svn/mechatronics/guides/ipfwpaper.cls $
  \RequirePackage{svn-multi}
}{ % If svninfo not installed, we load it invisibly to avoid problems
  %\RequirePackage[notoday,nofancy,final]{svninfo} 
    \RequirePackage{svn-multi}
}

\RequirePackage{fancyhdr}
%% Nice options for tweaking the header and footer

\PassOptionsToPackage{final}{graphicx}
\RequirePackage{graphicx}
%% We give the "final" option so that it always includes graphics
%% Take out this option if you want draft mode to skip pics

\DeclareGraphicsExtensions{.pdf,.jpeg,.jpg,.png,.gif,.PDF,.JPEG,.JPG,.PNG,.GIF}
%% The extensions that it will search for as valid graphics files
%% We need To include the UPPERCASE versions For windows people

\graphicspath{{graphics/}{Graphics/}{./}}
%% This is a list of folders to search for graphics files to include
%% Each path should be in a {}.  
%% Make sure that the upper/lowercase of the letters matches the folder or
%% you may have weird problems with partners using other operating systems.
%% Note that you don't have to put the folder where the .tex file is, it is
%% included automatically, though it is bad practice to put your 
%% graphics there

\PassOptionsToPackage{final}{pdfpages}
\RequirePackage{pdfpages}
%% The "pdfpages" package lets you include PDF documents into your
%% document but keeps the formatting consistent (headers, footers,
%% etc.)

%\RequirePackage{lastpage}
% The "lastpage" package allows you to set a command that knows
% what the last page was e.g. page 1 of 4

%% declare the paths(s) where you graphics files can be found
%% This allows you to skip putting the full path in
%% If you put a // at the end, it will recursively search for graphics
%% files, but be careful!
\RequirePackage{pseudocode}


%% The url package typesets url's and unix file paths nicely when they
%% become long.  Warning: windows paths such as C:\window do not work!
%% You need another verbatime to make those typeset properly.
\PassOptionsToPackage{obeyspaces,spaces,hyphens,allowmove}{url}
% first pass options to deal with spaces and hyphens properly
% allowmove: allow URLs in fragile commands but may break very badly
\RequirePackage{url}


%% The xspace package will try to figure out if you meant to have a
%% space after a new command if you don't give it any arguments (in
%% braces: {} ).  
\RequirePackage{xspace}

%% By default, set to 1.5 spacing
\linespread{1.3}  % this method does not require the setspace package

% % some font package possibilities
% % From http://www.macfreek.nl/memory/Fonts_in_LaTeX
%\RequirePackage{lmodern}
%\RequirePackage{times}
%\RequirePackage{palatino}
%\RequirePackage{bookman}
%\RequirePackage{newcent}
\RequirePackage{helvet}

% The old template uses san-serif, let's also do so 
\renewcommand{\familydefault}{\sfdefault}

%% ---------------- Main Code -----------------------------
%%  Remember that we can declar commands (to make fragile things safe)
%%  The * form is for ``short'' commands, not longer than a paragraph
%%       \DeclareRobustCommand{cmd}[num][default]{definition}
%%       \DeclareRobustCommand*{cmd}[num][default]{definition}
%%  For checking if commands are redefined
%%       \CheckCommand{cmd}[num][default]{definition}

% this is for the picture environment
\setlength{\unitlength}{1cm}

%% AMS is American Mathematics Society.  They have a bunch of nice
%% math fonts and symbols
\RequirePackage{amsfonts}
\RequirePackage{amsmath}
\RequirePackage{amssymb}

%% Advanced matrix formating
\RequirePackage{array}

%% Helpful math tools
\RequirePackage{mathtools}

%% Some symbols that don't exist in all fonts
\RequirePackage{textcomp}

%% Units formatting according to SI rules
\RequirePackage{siunitx} 

% another verbatim environment
\RequirePackage{examplep} 

\PassOptionsToPackage{final}{listings}
\RequirePackage{listings} % force final or included files are skipped, invisibly!
% This allows you to include code easily
% \begin{lstlisting}[language=bash]
%  $ wget http://blah
% \end{lstlisting}
% or for simpler snippets
%  \lstinline^cp -rp foo bar^
%  You can replace ^ with any character not in your code
\lstset{%
        language=, %default similar to verbatim
  basicstyle=\footnotesize\ttfamily,%
  frame=lines,%
  breaklines=true,%
  postbreak=\raisebox{0ex}[0ex][0ex]{\ensuremath{\color{red}\hookrightarrow\space}}, % red arrow
  % from http://tex.stackexchange.com/questions/116534/lstlisting-line-wrapping
}
% Here are listings macros I use a lot
\newcommand{\fpath}{\lstinline}  % magic, do not try to give it arguments
\newcommand{\shcmd}{\lstinline}  
% ex:  \fpath{\\AFS\.rnd.ru.is\course\T-411-MECH}


%% Nice package for making sub figures inside of a figure.  Keeps all
%% the reference labels correct and makes "sub captions"
% % WARNING: this is completely different than the subfigure package
\RequirePackage{subfig}



% FIXME system for making notes to each other for what needs to 
% be completed in a group document.
\RequirePackage[layout=inline]{fixme}

% internal layouts: inline, margin, footnote, index, marginclue
% % Warning! The pdf external layouts do not play well with hyperref
% % and must be loaded by doing \fxuselayouts{}
% external layouts: marginnote, pdfnote, pdfmargin, pdfsignote, pdfsigmargin
%     pdfcnote, pdfcmargin, pdfcsignote, pdfcsigmargin
%  pdfc stands for pdf color
%\fxuselayouts{pdfcmargin}

% Pass options for hyperlinks (which must be just before \begin{document}
%   final: always put hyperlinks
%   hidelinks: don't change color or text on hyperlink
\PassOptionsToPackage{final,hidelinks}{hyperref}



%% The "xcolor" package allows us to set the color of text and other
%% objects in a document
\RequirePackage{xcolor}
\definecolor{rugray}{rgb}{0.2,0.2,0.2}
\definecolor{rured}{rgb}{0.84,0.10,0.13}

%% The booktabs package makes book-quality tables by adjusting spacing
%% \toprule \midrule \bottomrule
%% \cmidrule[<wd>](<trim>){a–b}
%% \addlinespace[<wd>] % extra space between rows
\RequirePackage{booktabs}

%% ----- Setup page layout, including title page ----- %%

%% First we need to create some "variables" with defaults and commands
%% defaults
\newcommand{\title@ipfwpaper}{Report Template}
\newcommand{\shorttitle@ipfwpaper}{Template}
\newcommand{\author@ipfwpaper}{David S. Cochran}
\newcommand{\course@ipfwpaper}{\quad}
\newcommand{\revision@ipfwpaper}{}
%% TODO: figure out a better way to do optional elements on title page such as course
\newcommand{\department@ipfwpaper}{IPFW Electrical and Computer Engineering}
\newcommand{\instructor@ipfwpaper}{}
\newcommand{\teachingassistant@ipfwpaper}{}
\newcommand{\distribution@ipfwpaper}{}

\newcommand{\formatemail}[1]{$<$\texttt{#1}$>$}

%% commands to set them
\newcommand{\shorttitle}[1]{\renewcommand{\shorttitle@ipfwpaper}{\ifdraft{\color{rured}DRAFT:}{} #1}}
\renewcommand{\title}[1]{\renewcommand{\title@ipfwpaper}{#1}\shorttitle{#1}}
% assume that the short title should be same as normal title unless specified
\renewcommand{\author}[1]{\renewcommand{\author@ipfwpaper}{#1}}
\newcommand{\course}[1]{\renewcommand{\course@ipfwpaper}{#1}}
\newcommand{\department}[1]{\renewcommand{\department@ipfwpaper}{#1}}
\newcommand{\instructor}[1]{\renewcommand{\instructor@ipfwpaper}{Revision #1\\}}
\newcommand{\revision}[1]{\renewcommand{\revision@ipfwpaper}{Revision #1\\}}
\newcommand{\teachingassistant}[1]{\renewcommand{\teachingassistant@ipfwpaper}{TA:\@ #1\\}}
\newcommand{\distribution}[1]{\renewcommand{\distribution@ipfwpaper}{#1}}

\renewcommand{\maketitle}{
  {\centering%
    {\LARGE
      \vspace*{4cm}%
      \includegraphics{university-logo}\\\vspace{\baselineskip}
      \ifdraft{\fbox{\color{rured}DRAFT!  DO NOT SUBMIT!}\\}{}
      {\huge \title@ipfwpaper\\}
      \author@ipfwpaper\\
      \vspace{\baselineskip}
      \course@ipfwpaper\\
%      \department@ipfwpaper\\
      \instructor@ipfwpaper
      \teachingassistant@ipfwpaper
    }
    {\large
      \vfil
      \today\\
        \ifthenelse{\boolean{svn}}{SVN Revision \svnrev}{}  
      \vfil
    }
    % \ifthenelse{\boolean{svn}}{{\scriptsize \url{\svnmainurl}}}{}
    \distribution@ipfwpaper{}
  }
  \newpage
}

\pagestyle{fancyplain} % rest of the document should have fancy headers
                       % but not too fancy
\thispagestyle{empty} % title page should not have the headers and
                      % footers

\lhead{\shorttitle@ipfwpaper}
\rhead{\leftmark}
\setlength{\headheight}{15pt} % to make fancyhdr happy

%% on the rest of the pages, these will be the headers.  The first letter
%% indicates which side.  "r" for right, "c" for center, "l" for left 
%\lfooter{}
%\rfooter{}
%% set these for if you need to adjust the footers. 


%% This will load custom.tex with your custom macros and definitions if it exists
%% We load this last so that it can overrite any existing settings
\IfFileExists{custom.tex}{\input{custom.tex}}{}

%% ---------------- End of Class --------------------------

