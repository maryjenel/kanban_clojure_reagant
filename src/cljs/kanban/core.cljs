(defn Card [card]
  (if (:editing card)
    [:div.card.editing [:input {:type "text" :value (:title card)}]]
    [:div.card (:title card)]))

(defn NewCard []
  [:div.new-card
   "+ add new card"])

(defn Column [{:keys [title cards editing]}]
  [:div.column
   (if editing
     [:input {:type "text" :value title}]
     [:h2 title])
   (for [c cards]
     [Card c])
   [NewCard]])

(defn NewColumn []
  [:div.new-column
   "+ add new column"])

(defn Board [state]
  [:div.board
   (for [c (:columns @state)]
   [Column])
   [NewColumn]])

(def app-state
  (r/atom {:columns
           [{:title "Todos"
             :cards [{:title "Learn about Reagent"}
                     {:title "Tell my friends about Lambda Island"}]}]}))

(ns kanban.core
  (:require [reagent.core :as r]))

;;(defn set-title! [card title]
;;(swap! card assoc :title title))

;; same as
(defn set-title! [card title]
  (swap! card (fn [c] (assoc c :title title))))

(get-in @board [:columns 1 :cards 0 :title]) ;;=> "Hello Turtles."
(swap! board assoc-in [:columns 1 :cards 0 :title] "Hello Turtles.")


(set-title! card-atom "Hello Turtles")

card-atom

(enable-console-print!)

(r/render [Board app-state] (js/document.getElementById "app"))
