package com.ai.springaipractice;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class RecipeService{
    private final ChatModel chatModel;

    public RecipeService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }
    public String createRecipe(String ingredients, String cuisine, String dietaryRestrictions) {
        var template = """
                I want to create a recipe using the following ingredients: {ingredients}.
                The cuisine type I prefer is {cuisine} .
                Please consider the following dietary restrictions:{dietaryRestrictions}
                
                Please provide:
                1. Recipe name
                2. List of ingredients with quantities
                3. Step by step cooking instructions
                4. Cooking time
                5. Serving size
                """;
        PromptTemplate promptTemplate = new PromptTemplate(template);
        Map<String, Object> params = Map.of(
                "ingredients",ingredients,
                "cuisine",cuisine,
                "dietaryRestrictions",dietaryRestrictions
        );
        Prompt prompt = promptTemplate.create(params);

        return chatModel.call(prompt).getResult().getOutput().getText();
    }
}
