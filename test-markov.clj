(use 'clojure.test)
(load-file "markov.clj")


(deftest split-input-test
	;(println (split-input "I am not a number!"))
	(is (= 
		'(" " " " "I" "am" "not" "a" "number!" " " " ") 
		(split-input "I am not a number!")
	))

	(is (=
		"I"
		(nth (split-input "I am not a number!") 2) 
	))
)


(deftest threegrams-test
	;(println (threegrams (split-input "I am not a number!")))
	(let [grams (threegrams (split-input "I am not a number!"))]
		;(println (first grams))
		(is (=
			["I"]
			((first grams) "   ")
		))

		;(println (nth grams 2))
		(is (=
			["not"]
			((nth grams 2) "I am")
		))
	)
)




(run-all-tests #"user")