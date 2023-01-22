package src;
import libs.vec3;
import libs.utils;

public abstract class RenderableObject extends Object{
  private vec3 color;
  private long time;

  public abstract float signedDist(vec3 point);

  public float signedDistSine(vec3 point) {
    return this.signedDist(point) + utils.sineDisplace(point, time);
  }

  public vec3 getColor() {
    return this.color;
  }

  public void setColor(vec3 color) {
    this.color = color;
  }

  public vec3 estimateNormal(vec3 p) {
    final float EPSILON = 0.01f;
    // return new vec3(
    //     signedDist(new vec3(p.x + EPSILON, p.y, p.z)) - signedDist(new vec3(p.x - EPSILON, p.y, p.z)),
    //     signedDist(new vec3(p.x, p.y + EPSILON, p.z)) - signedDist(new vec3(p.x, p.y - EPSILON, p.z)),
    //     signedDist(new vec3(p.x, p.y, p.z  + EPSILON)) - signedDist(new vec3(p.x, p.y, p.z - EPSILON))
    // ).normalize();
  
    return new vec3(
        signedDistSine(new vec3(p.x + EPSILON, p.y, p.z)) - signedDistSine(new vec3(p.x - EPSILON, p.y, p.z)),
        signedDistSine(new vec3(p.x, p.y + EPSILON, p.z)) - signedDistSine(new vec3(p.x, p.y - EPSILON, p.z)),
        signedDistSine(new vec3(p.x, p.y, p.z  + EPSILON)) - signedDistSine(new vec3(p.x, p.y, p.z - EPSILON))
    ).normalize();
  }

  public void setTime(long time) {
    this.time = time;
  }
  public long getTime() {
    return this.time;
  }
}