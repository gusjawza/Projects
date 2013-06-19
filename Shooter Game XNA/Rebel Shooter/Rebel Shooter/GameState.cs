using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;

namespace Rebel_Shooter
{
    public abstract class GameState
    {
        //public Player player;

        protected Game1 Game { get; set; }

        public GameState(Game1 game)
        {
            Game = game;
        }

        public virtual void Load() { }

        public abstract void Update(GameTime gameTime);
        public abstract void Draw(GameTime gameTime, SpriteBatch spriteBatch);

        //public void Initialize()
        //{
        //    // TODO: Add your initialization logic here
        //    // Initialize the player class
        //    player = new Player();

        //    // Set a constant player move speed
        //    playerMoveSpeed = 8.0f;
        //    // Initialize the enemies list
        //    enemies = new List<Enemy>();

        //    // Set the time keepers to zero
        //    previousSpawnTime = TimeSpan.Zero;

        //    // Used to determine how fast enemy respawns
        //    enemySpawnTime = TimeSpan.FromSeconds(1.0f);
        //    projectiles = new List<Projectile>();

        //    // Set the laser to fire every quarter second
        //    fireTime = TimeSpan.FromSeconds(.15f);
        //    // Initialize our random number generator
        //    random = new Random();


        //    //base.Initialize();
        //}


         Player player { get; set; }

        public float playerMoveSpeed { get; set; }

        internal List<Enemy> enemies { get; set; }

        public TimeSpan previousSpawnTime { get; set; }

        internal TimeSpan enemySpawnTime { get; set; }

        internal List<Projectile> projectiles { get; set; }

        public TimeSpan fireTime { get; set; }

        public Random random { get; set; }

          }
}
