using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;

namespace Rebel_Shooter
{
    public class IntroState : GameState
    {

        SpriteFont font = null;
        Texture2D image1 = null;
        Texture2D image2 = null;
        Rectangle location;
        Rectangle location2;
        MouseState mouse;
        MouseState oldMouse;


        public IntroState(Game1 game) :
            base(game)
        {
        }
        public override void Load()
        {
            image1 = Game.Content.Load<Texture2D>("Button1");
            image2 = Game.Content.Load<Texture2D>("Button2");
            font = Game.Content.Load<SpriteFont>("font");
            location = new Rectangle(280, 120, image1.Width, image1.Height);
            location2 = new Rectangle(280, 210, image1.Width, image2.Height);
        }

        public override void Update(GameTime gameTime)
        {

            mouse = Mouse.GetState();

            if (mouse.LeftButton == ButtonState.Released && oldMouse.LeftButton == ButtonState.Pressed)
            {
                if (location.Contains(new Point(mouse.X, mouse.Y)))
                {
                    Game.SetState(new MenuState(Game));
                }
                if (location2.Contains(new Point(mouse.X, mouse.Y)))
                {
                    Game.Exit();
                }
            }
            oldMouse = mouse;
            // if (Keyboard.GetState().IsKeyDown(Keys.EC:\Users\jawza\Desktop\Game_Rebel_shooter_1.0_Version\Rebel Shooter\Rebel Shooter\Rebel Shooter\Program.csscape))
            // {
            //   Game.Exit();
            //}
        }

        public override void Draw(GameTime gameTime, SpriteBatch spriteBatch)
        {
            spriteBatch.Draw(image1, location, Color.White);
            spriteBatch.Draw(image2, location2, Color.White);
        }
    }
}
