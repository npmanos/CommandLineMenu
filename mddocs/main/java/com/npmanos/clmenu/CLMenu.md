# [CLMenu](../main/java/com/npmanos/clmenu/CLMenu.java#L10)

**Type:** `public` `class`

A simple interactive command line menu. 












## [display](../main/java/com/npmanos/clmenu/CLMenu.java#L32)

**Type:** `public` `void`

Displays menu options and prompts user to select. 

<p>When option is selected, runnable is executed and user is returned 
to the menu. The menu is displayed repeatedly until user selects return/quit. 












## [select](../main/java/com/npmanos/clmenu/CLMenu.java#L63)

**Type:** `private` `int`

Take user input, and ensure input is a number and one of the options. 






**Returned Value:** Selected option number  








# [Builder](../main/java/com/npmanos/clmenu/CLMenu.java#L89)

**Type:** `public` `static` `final` `class`

Build a new .












## [addOption](../main/java/com/npmanos/clmenu/CLMenu.java#L100)

**Type:** `public` `Builder`

Add an option to the menu. 





|Parameter Name|Description|
|-----|-----|
|description|Option title to display|
|action |to execute when option is selected|


**Returned Value:** This builder for chaining  








## [addSubmenu](../main/java/com/npmanos/clmenu/CLMenu.java#L112)

**Type:** `public` `Builder`

Add another as a submenu. When user selects this option, 
the submenu will be displayed. When user exits the submenu, they will 
be returned to this menu. 





|Parameter Name|Description|
|-----|-----|
|submenu |to add as a submenu|


**Returned Value:** This builder for chaining  








## [build](../main/java/com/npmanos/clmenu/CLMenu.java#L126)

**Type:** `public` `CLMenu`

Builds with specified options. 












