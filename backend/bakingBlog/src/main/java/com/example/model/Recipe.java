package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="recipes")
@Data
@Getter
@Setter

public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(length = 100)
    private String description;

    private Integer prepTime;

    private Integer cookTime;

    private Integer servings;

    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @Column(nullable = false)
    private String instructions;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "category_id", referencedColumnName = "id")
     private Category category;

     @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
     private List<RecipeIngredient> recipeIngredients = new ArrayList<>();

     public void addRecipeIngredient(RecipeIngredient recipeIngredient) {
        recipeIngredients.add(recipeIngredient);
        recipeIngredient.setRecipe(this);
         }

     public void removeRecipeIngredient(RecipeIngredient recipeIngredient){
        recipeIngredients.remove(recipeIngredient);
        recipeIngredient.setRecipe(this);
     }

     public Recipe(Long id){
        this.id = id;
     }

     public Recipe(Long id, String title, String description, Integer prepTime, Integer cookTime, Integer servings){
        this.id = id;
        this.title = title;
        this.description = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        }

      @override
      public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof Recipe)){
           return false;
           }
           Recipe recipe = (Recipe) o;
           return getId()!=null && Objects.equals(getId(), recipe.getId());
      }

      @override
      public int hashCode(){
        return getClass().hashCode();
      }

}