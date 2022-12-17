package com.nana;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;

public class TileMapHelper{
    private TiledMap tiledMap;
    
    public TileMapHelper(){

    }

    public OrthoCachedTiledMapRenderer setupMap(){
        tiledMap = new TmxMapLoader().load("assets/maps/tutorial.tmx");
        return new OrthoCachedTiledMapRenderer(tiledMap);
    }
    
}