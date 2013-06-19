using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Audio;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.GamerServices;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using Microsoft.Xna.Framework.Media;

namespace Rebel_Shooter
{
    public class MenuState : GameState
    {
        GraphicsDeviceManager graphics;
        SpriteBatch spriteBatch;
        // Represents the player
         Player player;
        // Keyboard states used to determine key presses
        KeyboardState currentKeyboardState;
        KeyboardState previousKeyboardState;
        // A movement speed for the player
        new public float playerMoveSpeed;
        Texture2D projectileTexture;
        new List<Projectile> projectiles;

        // The rate of fire of the player laser
        new TimeSpan fireTime;
        TimeSpan previousFireTime;

        // Enemies
        Texture2D enemyTexture;
        new List<Enemy> enemies;

        // The rate at which the enemies appear
        new TimeSpan enemySpawnTime;
        new TimeSpan previousSpawnTime;
        // The music played during gameplay
        Song gameplayMusic;

        // A random number generator
        new Random random;
        public MenuState(Game1 game) :
            base(game)
        {
            this.game = game;
            PlayMusic(gameplayMusic);
        }
        /// <summary>
        /// Allows the game to perform any initialization it needs to before starting to run.
        /// This is where it can query for any required services and load any non-graphic
        /// related content.  Calling base.Initialize will enumerate through any components
        /// and initialize them as well.
        /// </summary>
        public void Initialize()
        {
            // TODO: Add your initialization logic here
            // Initialize the player class
            player = new Player();

            // Set a constant player move speed
            playerMoveSpeed = 8.0f;
            // Initialize the enemies list
            enemies = new List<Enemy>();

            // Set the time keepers to zero
            previousSpawnTime = TimeSpan.Zero;

            // Used to determine how fast enemy respawns
            enemySpawnTime = TimeSpan.FromSeconds(1.0f);
            projectiles = new List<Projectile>();

            // Set the laser to fire every quarter second
            fireTime = TimeSpan.FromSeconds(.15f);
            // Initialize our random number generator
            random = new Random();


            
        }

        /// <summary>
        /// LoadContent will be called once per game and is the place to load
        /// all of your content.
        /// </summary>
        public override void Load()
        {
            // Create a new SpriteBatch, which can be used to draw textures.
            spriteBatch = new SpriteBatch(Game.GraphicsDevice);
            // Load the player resources
            // Load the music
            gameplayMusic = Game.Content.Load<Song>("Sound/Background");

            Vector2 playerPosition = new Vector2(Game.GraphicsDevice.Viewport.TitleSafeArea.X, Game.GraphicsDevice.Viewport.TitleSafeArea.Y + Game.GraphicsDevice.Viewport.TitleSafeArea.Height / 2);
            player.Initialize(Game.Content.Load<Texture2D>("shooter"), playerPosition);
            enemyTexture = Game.Content.Load<Texture2D>("Tank1");
            projectileTexture = Game.Content.Load<Texture2D>("bullet");
            // Start the music right away
            PlayMusic(gameplayMusic);
            // TODO: use this.Content to load your game content here
        }
        private void PlayMusic(Song song)
        {
            // Due to the way the MediaPlayer plays music,
            // we have to catch the exception. Music will play when the game is not tethered
            try
            {
                // Play the music
                MediaPlayer.Play(song);

                // Loop the currently playing song
                MediaPlayer.IsRepeating = true;
            }
            catch { }
        }
        private void AddProjectile(Vector2 position)
        {
            Projectile projectile = new Projectile();
            projectile.Initialize(Game.GraphicsDevice.Viewport, projectileTexture, position);
            projectiles.Add(projectile);
        }
        private void AddEnemy()
        {
            // Create the animation object
            //  Animation enemyAnimation = new Animation();

            // Initialize the animation with the correct animation information
            // enemyAnimation.Initialize(enemyTexture, Vector2.Zero, 47, 61, 8, 30, Color.White, 1f, true);

            // Randomly generate the position of the enemy
            Vector2 position = new Vector2(Game.GraphicsDevice.Viewport.Width + enemyTexture.Width / 2, random.Next(100, Game.GraphicsDevice.Viewport.Height - 100));

            // Create an enemy
            Enemy enemy = new Enemy();

            // Initialize the enemy
            enemy.Initialize(enemyTexture, position);

            // Add the enemy to the active enemies list
            enemies.Add(enemy);
        }
        private void UpdateEnemies(GameTime gameTime)
        {
            // Spawn a new enemy enemy every 1.5 seconds
            if (gameTime.TotalGameTime - previousSpawnTime > enemySpawnTime)
            {
                previousSpawnTime = gameTime.TotalGameTime;

                // Add an Enemy
                AddEnemy();
            }


            // Update the Enemies
            for (int i = enemies.Count - 1; i >= 0; i--)
            {
                enemies[i].Update(gameTime);

                if (enemies[i].Active == false)
                {
                    enemies.RemoveAt(i);
                }
            }
        }


        /// <summary>
        /// Allows the game to run logic such as updating the world,
        /// checking for collisions, gathering input, and playing audio.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        public override void Update(GameTime gameTime)
        {
            // Allows the game to exit
            if (GamePad.GetState(PlayerIndex.One).Buttons.Back == ButtonState.Pressed)
                Game.Exit();

            // TODO: Add your update logic here

            // Read the current state of the keyboard and gamepad and store it
            currentKeyboardState = Keyboard.GetState();

            //Update the player
            UpdatePlayer(gameTime);
            // Update the enemies
            UpdateEnemies(gameTime);
            // Update the projectiles
            UpdateProjectiles();


            //base.Update(gameTime);
        }

        private void UpdatePlayer(GameTime gameTime)
        {
            throw new NotImplementedException();
        }

        private void UpdatePlayer(GameTime gameTime , GraphicsDevice GraphicsDevice)
        {

            // Fire only every interval we set as the fireTime
            if (gameTime.TotalGameTime - previousFireTime > fireTime)
            {
                if (currentKeyboardState.IsKeyDown(Keys.Space))
                {

                    // Reset our current time
                    previousFireTime = gameTime.TotalGameTime;

                    // Add the projectile, but add it to the front and center of the player
                    AddProjectile(player.Position + new Vector2(player.Width / 2, 0));
                }

            }

            // Use the Keyboard / Dpad
            if (currentKeyboardState.IsKeyDown(Keys.Left))
            {
                player.Position.X -= playerMoveSpeed;
            }
            if (currentKeyboardState.IsKeyDown(Keys.Right))
            {
                player.Position.X += playerMoveSpeed;
            }
            if (currentKeyboardState.IsKeyDown(Keys.Up))
            {
                player.Position.Y -= playerMoveSpeed;
            }
            if (currentKeyboardState.IsKeyDown(Keys.Down))
            {
                player.Position.Y += playerMoveSpeed;
            }

            // Make sure that the player does not go out of bounds
            player.Position.X = MathHelper.Clamp(player.Position.X, 0, GraphicsDevice.Viewport.Width - player.Width);
            player.Position.Y = MathHelper.Clamp(player.Position.Y, 0, GraphicsDevice.Viewport.Height - player.Height);
        }
        private void UpdateProjectiles()
        {
            // Update the Projectiles
            for (int i = projectiles.Count - 1; i >= 0; i--)
            {
                projectiles[i].Update();

                if (projectiles[i].Active == false)
                {
                    projectiles.RemoveAt(i);
                }
            }
        }

        /// <summary>
        /// This is called when the game should draw itself.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        public override void Draw(GameTime gameTime, SpriteBatch spriteBatch)
        {
            Game.GraphicsDevice.Clear(Color.Black);
            // Start drawing
            spriteBatch.Begin();

            // Draw the Player
            player.Draw(spriteBatch);
            // Draw the Enemies
            for (int i = 0; i < enemies.Count; i++)
            {
                enemies[i].Draw(spriteBatch);
            }
            for (int i = 0; i < projectiles.Count; i++)
            {
                projectiles[i].Draw(spriteBatch);
            }
            // Stop drawing
            spriteBatch.End();
            // TODO: Add your drawing code here

                }





        public Game1 game { get; set; }
    }
}
