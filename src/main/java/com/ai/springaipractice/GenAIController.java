package com.ai.springaipractice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenAIController {

    private final ChatService chatService;
    private final RecipeService recipeService; // ← ADD KARO

    public GenAIController(ChatService chatService, RecipeService recipeService) { // ← ADD KARO
        this.chatService = chatService;
        this.recipeService = recipeService; // ← ADD KARO
    }

    @GetMapping("ask-ai")
    public String getResponse(@RequestParam String prompt){
        return chatService.getResponse(prompt);
    }

    @GetMapping("ask-ai-options")
    public String getResponseOption(@RequestParam String prompt){
        return chatService.getResponseOption(prompt);
    }

    @GetMapping("/recipe")
    public String getRecipe(
            @RequestParam String ingredients,
            @RequestParam String cuisine,
            @RequestParam String dietaryRestrictions) {
        return recipeService.createRecipe(ingredients, cuisine, dietaryRestrictions); // ← lowercase
    }
}