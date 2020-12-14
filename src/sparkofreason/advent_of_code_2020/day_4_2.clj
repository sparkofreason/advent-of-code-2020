(ns sparkofreason.advent-of-code-2020.day4-2
  (:require [clojure.string :as string]
            [clojure.spec.alpha :as s]
            [sparkofreason.advent-of-code-2020.day4-1 :as day4-1]))
            
(defn yr?
  [min max y]
  (and (= 4 (count y))
       (try
         (let [y' (Long/parseLong y)]
           (<= min y' max))
         (catch Exception _
           false))))

(def units? #{"in" "cm"})

(defn parse-hgt
  [s]
  (let [units (subs s (max 0 (- (count s) 2)))]
    (if (units? units)
      (try
        (let [h (Long/parseLong (subs s 0 (- (count s) 2)))]
          [h units])
        (catch Exception _
          false))
      [])))

(defn hgt?
  [h]
  (let [[x unit] (parse-hgt h)]
    (condp = unit
      "cm" (<= 150 x 193)
      "in" (<= 59 x 176)
      false)))

(defn hcl?
  [x]
  (some? (re-find #"^#[0-9a-f]{6}$" x)))

(defn pid?
  [x]
  (some? (re-find #"^[0-9]{9}$" x)))

(s/def :d4/byr (partial yr? 1920 2002))
(s/def :d4/iyr (partial yr? 2010 2020))
(s/def :d4/eyr (partial yr? 2020 2030))
(s/def :d4/hgt hgt?)
(s/def :d4/ecl #{"amb" "blu" "brn" "gry" "grn" "hzl" "oth"})
(s/def :d4/hcl hcl?)
(s/def :d4/pid pid?)

(->> day4-1/passports
     (filter #(s/valid? ::day4-1/passport1 %))
     count)

