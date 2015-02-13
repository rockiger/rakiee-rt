(ns rakiee.data-definitions)
;; =================
;; Data definitions:

;; TaskState is one of:
;; - "TODO"
;; - "DOING"
;; - "DONE"
;; interp. as the current state of a task

#_
(defn fn-for-taskstate [ts]
  (cond
   (= ts TODO)  (true)
   (= ts DOING) (true)
   (= ts DONE)  (true)))
