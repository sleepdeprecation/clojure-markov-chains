(use '[clojure.set :only (union)])
(use '[clojure.string :only (join split trim)])

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


; Turn [input] (a seq of words) into the chain (pseudo 3-grams)
	; Creates a map with keys of all two-word groups in [input],
	; with the values being a vector of following words.
	;
	; The map looks like:
	; { "[word 1] [word 2]" : ["[word 3, 1]", "[word 3, 2]" ...] }
(defn threesies [input]
	(if (< (count input) 3)
		nil
		(merge-with union 
			{(join " " (take 2 input)) [(nth input 2)]} 
			(threesies (rest input))
		)
	)
)


(defn next-word [chain words]
	(let [c (count words)]
		(let [k (join " " [(nth words (- c 2)) (nth words (- c 1))])]
			(rand-nth (seq (chain k)))
		)
	)
)


(defn create-seq [chain words]
	(let [nw (next-word chain words)]
		(cons nw (lazy-seq (create-seq chain (concat words [nw]))))
	)
)


(defn generate [input length]
	(let [chain (threesies (split-input input))]
		(join 
			" "
			(filter 
				(fn [item] (not (empty? item)))
				(map trim
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
	)
)
(println (generate (slurp "arcade-fire.txt") 10000))
