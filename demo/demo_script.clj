(ns demo-script
  (:require [demo-script :refer :all]
            [midje.sweet :refer :all]))


(require '[com.billpiel.sayid.core :as sd])

sd/src-in-meta
(defn func-example1 [a b]
 (* a b))

sd/src-in-meta
(defn func-example2
  [a b]
  (if (< a b)
    (-> a
        inc
        (func-example1 b)
        (* 2)
        (vector a b))
    (recur a (inc b))))

(sd/ws-add-trace-ns! user)

(func-example2 1 2)

(sd/ws-print)

(func-example2 3 1)

(sd/ws-print)

(sd/ws-add-deep-trace-fn! func-example2)

(sd/ws-clear-log!)

(func-example2 3 1)

(sd/ws-print)

(sd/p-t (sd/qw [:name 'inc]))

(sd/p-t (sd/qw :a [:name 'inc]))

;; get parent and child
(sd/q :ad 1 [:return 3])

;; getting a value
(def ret (-> (sd/qw func)
             first
             :return))

;; ### profiling

(doc sd/pro-net-time)

(doc sd/pro-gross-repeats)

(def ppp (sd/pro-analyse (sd/ws-deref!)))

(sd/pro-gross-repeats ppp)


;; ### recordings
