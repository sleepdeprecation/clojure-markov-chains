(use '[clojure.set :only (union)])
(use '[clojure.string :only (join split blank?)])

; Turn [input] into a parsable seq.
	; adds two empty slots before and after [input] so we can
	; loop around (this is due to how the chain is setup).
(defn split-input [input]
	(concat 
		'(" " " ") 
		(seq (split input #" ")) 
		'(" " " ")
	)
)


; Generate pseudo 3-grams.
	; Creates a lazy-seq of maps (each with only one key
	; and value).
	;
	; These maps look like:
	;	{ "[word 1] [word 2]": "[word 3]"}
(defn threegrams [input]
	(if (< (count input) 3)
		nil
		(lazy-seq (cons 
			{(join " " (take 2 input)) [(nth input 2)]}
			(threegrams (rest input))
		))
	)
)


; Given a sequence of 3-grams, create a chain
	; Chains are just a union of all 3-grams produced by 
	; `threegrams`
(defn chain-gen [input]
	(reduce 
		#(merge-with union %1 %2) 
		(threegrams (split-input input))
	)
)


; Given a chain and seq of words, generate a potential next word
(defn next-word [chain words]
	(let [c (count words)]
		(rand-nth 
			(chain (join " " (take-last 2 words)) )
		)
	)
)


; Given a chain and a seq of words, generate more words
(defn create-seq [chain words]
	(let [nw (next-word chain words)]
		(cons 
			nw 
			(lazy-seq (create-seq chain (concat words [nw])))
		)
	)
)


; Turn a seq into real words...
(defn stringiffy [words]
	(join 
		" "
		(filter #(not (blank? %)) words)
	)
)


; Generate `length` words based on an input string
(defn generate [input length]
	(let [chain (chain-gen input)]
		(stringiffy
			(take 
				length 
				(create-seq 
					chain 
					;'(" " " ")
					(split (rand-nth (keys chain)) #" ")
				)
			)
		)
	)
)
;(println (generate (slurp "txt/lovecraft-at-the-mountains-of-madness.txt") 100))
(println (generate (slurp "txt/mahoney-multiposts.txt") 100))
