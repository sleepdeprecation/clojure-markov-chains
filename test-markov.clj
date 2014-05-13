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


(deftest threesies-test
	;(println (threesies (split-input "I am not a number! I am a free man!")))
	(let [chain (threesies (split-input "I am not a number! I am a free man!"))]
		(is (=
			["not" "a"]
			(chain "I am")
		))

		(is (=
			["free"]
			(chain "am a")
		))
	)
)


(deftest next-word-test
	(let [chain (threesies (split-input "I am not a number!"))]
		(is (=
			"I"
			(next-word chain '(" " " "))
		))

		(is (=
			"not"
			(next-word chain '(" " " " "I" "am"))
		))
	)
)


(deftest create-seq-test
	(let [chain (threesies (split-input "I am not a number!"))]
		(is (=
			"I am not a number!"
			(join 
				" "
				(take 5 (create-seq chain '(" " " ")))
			)
		))
	)
)

(run-all-tests #"user")