(deftemplate flag (slot country-name) (multislot flag-colors))

(deffacts flags 
(flag (country-name Egypt) (flag-colors red white black))
(flag (country-name United-States) (flag-colors Red white blue))
(flag (country-name Belgium) (flag-colors Black yellow red))
(flag (country-name Sweden) (flag-colors Yellow blue))
(flag (country-name Italy) (flag-colors Green white red))
(flag (country-name Ireland) (flag-colors Green white orange))
(flag (country-name Greece) (flag-colors Blue white))
)

"(deffacts start (get-color))

(defrule get-colors
   ?f <- (get-color)
   =>
   (retract ?f)
   (printout t "Flag color (done to end)?")
   (assert (new-color (read))))"



(defrule get-country (new-color ?color) (flag (country-name ?country) (flag-colors $?colors)) (test (member$ ?color ?colors))
=> 
(printout t "country is " ?country crlf))

