package CondiWarrior

import CondiWarrior.src.main.Weapons.{Longbow, Sword, Torch, Unarmed, Weapon}
import CondiWarrior.src.main.Entities.{CondiWarrior, Target}

object test1 {

  var condiWarrior: CondiWarrior = null
  val longbow = new Longbow()
  val sword = new Sword()
  val torch = new Torch()
  val unarmed = new Unarmed()

  val precision = 1719
  val ferocity = 206
  val conditionDamage = 3082
  val expertise = 779
  val power = 2868

  private val weapons = List[Weapon](longbow, sword, torch, unarmed)

  def main(args: Array[String]): Unit = {
    var damage = List[Double]()
    var dps = List[Double]()
    var times = List[Double]()

    for (i <- 1 to 100000) {

      val target = executeSequence(sword_offhand_41_2k)
      //val target = testSkill(longbow.dual_shot)

      damage = target.getDamageTaken :: damage
      times = target.getTime :: times
      dps = (target.getDamageTaken / target.getTime) :: dps

    }
    printResults(damage, times, dps)
  }

  def testSkill(skillUnderTest: Double => Unit): Target = {
    condiWarrior = new CondiWarrior(precision, ferocity, conditionDamage, expertise, power)
    val target = new Target(1000000.0)

    for(weapon <- weapons){
      weapon.setPlayer(condiWarrior)
      weapon.setTarget(target)
    }

    unarmed.berserk()

    while(target.getDamageTaken < target.getHealth) {
      skillUnderTest(0.0)
    }
    target
  }

  def executeSequence(rotation: Target => Unit): Target = {
    condiWarrior = new CondiWarrior(precision, ferocity, conditionDamage, expertise, power)
    val target = new Target(4000000.0)

    for (weapon <- weapons) {
      weapon.setPlayer(condiWarrior)
      weapon.setTarget(target)
    }

    rotation(target)

    target
  }

  def printResults(damage: List[Double], times: List[Double], dps: List[Double]): Unit = {
    println("DAMAGE DONE")
    println("AVG: " + damage.sum / damage.length)
    println("MAX: " + damage.max)
    println("MIN: " + damage.min)
    println("TIME")
    println("AVG: " + times.sum / times.length)
    println("MAX: " + times.max)
    println("MIN: " + times.min)
    println("DPS")
    println("AVG: " + dps.sum / dps.length)
    println("MAX: " + dps.max)
    println("MIN: " + dps.min)
  }

  def old_torch_rota(target: Target): Unit = {
    //Precast
    torch.flames_of_war(0.0)
    unarmed.swapWeapon(0.0)

    //LB 1
    longbow.fan_of_fire(0.560)
    longbow.pin_down(0.672)
    unarmed.head_butt(0.960)
    unarmed.berserk()
    unarmed.outrage()
    longbow.scorched_earth(0.368)
    unarmed.blood_reckoning(0.272)
    longbow.scorched_earth(0.369)
    unarmed.shattering_blow(0.511)
    unarmed.sundering_leap(0.960)
    longbow.fan_of_fire(0.369)
    unarmed.swapWeapon(0.191)

    //Sword 1
    torch.blaze_breaker(0.480)
    sword.savage_leap(1.008)
    sword.final_thrust(0.432)
    unarmed.wait(0.048)
    sword.flaming_flurry(1.680)
    unarmed.wait(0.032)
    sword.sever_artery(0.368)
    sword.gash(0.512)
    sword.hamstring(0.449)
    unarmed.outrage()
    unarmed.swapWeapon(0.111)

    //LB 2
    longbow.fan_of_fire(0.560)
    longbow.scorched_earth(0.368)
    longbow.arcing_arrow(0.560)
    longbow.dual_shot(0.832)
    longbow.dual_shot(0.848)
    longbow.arcing_arrow(0.720)
    longbow.scorched_earth(0.640)
    longbow.fan_of_fire(0.561)
    unarmed.swapWeapon(0.111)

    //Sword 2
    torch.blaze_breaker(0.480)
    torch.flames_of_war(0.528)
    unarmed.shattering_blow(0.512)
    unarmed.sundering_leap(0.960)
    sword.final_thrust(0.448)
    unarmed.outrage()
    unarmed.wait(0.080)
    sword.flaming_flurry(1.600)
    unarmed.wait(0.080)
    sword.sever_artery(0.321)
    unarmed.swapWeapon(0.319)

    //LB 3
    longbow.fan_of_fire(0.560)
    longbow.scorched_earth(0.352)
    unarmed.blood_reckoning(0.368)
    longbow.scorched_earth(0.352)
    longbow.pin_down(0.688)
    longbow.arcing_arrow(0.560)
    longbow.dual_shot(0.832)
    longbow.dual_shot(0.848)
    unarmed.wait(0.160)
    longbow.scorched_earth(0.352)
    longbow.fan_of_fire(0.449)
    unarmed.swapWeapon(0.111)

    //Sword 3
    unarmed.outrage()
    sword.savage_leap(1.008)
    torch.blaze_breaker(0.480)
    sword.final_thrust(0.432)
    unarmed.wait(0.048)
    sword.flaming_flurry(1.712)
    unarmed.wait(0.080)
    unarmed.shattering_blow(0.528)
    sword.sever_artery(0.352)
    sword.gash(0.448)
    unarmed.swapWeapon(0.240)

    //LB 4
    longbow.fan_of_fire(0.560)
    longbow.scorched_earth(0.352)
    longbow.arcing_arrow(0.560)
    unarmed.sundering_leap(1.008)
    longbow.arcing_arrow(0.720)
    unarmed.outrage()
    longbow.dual_shot(0.848)
    longbow.scorched_earth(0.352)
    longbow.fan_of_fire(0.561)
    unarmed.swapWeapon(0.111)

    //Sword 4
    sword.savage_leap(1.008)
    torch.blaze_breaker(0.480)
    torch.flames_of_war(0.513)
    sword.final_thrust(0.447)
    unarmed.wait(0.080)
    sword.flaming_flurry(1.712)
    unarmed.wait(0.048)
    sword.sever_artery(0.352)
    sword.gash(0.480)
    unarmed.swapWeapon(0.160)

    //LB 5
    longbow.fan_of_fire(0.560)
    longbow.scorched_earth(0.448)
    unarmed.outrage()
    unarmed.blood_reckoning(0.272)
    longbow.scorched_earth(0.368)
    longbow.pin_down(0.672)
    unarmed.shattering_blow(0.528)
    longbow.arcing_arrow(0.560)
    longbow.dual_shot(0.832)
    longbow.dual_shot(0.848)
    longbow.scorched_earth(0.352)
    longbow.fan_of_fire(0.400)
    unarmed.swapWeapon(0.208)

    //Sword 5
    unarmed.sundering_leap(0.960)
    sword.savage_leap(0.992)
    torch.blaze_breaker(0.480)
    unarmed.outrage()
    sword.final_thrust(0.448)
    unarmed.wait(0.080)
    sword.flaming_flurry(1.632)
    unarmed.wait(0.048)
    sword.sever_artery(0.352)
    unarmed.wait(0.080)
    unarmed.swapWeapon(0.128)

    //LB 6
    longbow.fan_of_fire(0.592)
    longbow.scorched_earth(0.368)
    longbow.arcing_arrow(0.560)
    longbow.dual_shot(0.832)
    longbow.dual_shot(0.848)
    longbow.arcing_arrow(0.592)
    unarmed.shattering_blow(0.528)
    longbow.scorched_earth(0.352)
    longbow.fan_of_fire(0.449)
    unarmed.swapWeapon(0.111)

    //Sword 6
    unarmed.outrage()
    sword.savage_leap(1.168)
    torch.flames_of_war(0.512)
    torch.blaze_breaker(0.480)
    sword.final_thrust(0.448)
    unarmed.wait(0.080)
    sword.flaming_flurry(1.680)
    unarmed.wait(0.032)
    sword.sever_artery(0.368)
    sword.gash(0.433)
    unarmed.swapWeapon(0.127)

    //LB 7
    longbow.fan_of_fire(0.560)
    longbow.scorched_earth(0.352)
    unarmed.blood_reckoning(0.320)
    longbow.scorched_earth(0.368)
    longbow.pin_down(0.672)
    longbow.arcing_arrow(0.560)
    unarmed.sundering_leap(1.040)
    unarmed.outrage()
    longbow.arcing_arrow(0.688)
    longbow.scorched_earth(0.352)
    longbow.fan_of_fire(0.401)
    unarmed.swapWeapon(0.159)

    //Sword 7
    sword.savage_leap(1.008)
    unarmed.shattering_blow(0.512)
    torch.blaze_breaker(0.480)
    sword.final_thrust(0.441)
    unarmed.wait(0.080)
    sword.flaming_flurry(1.680)
    unarmed.wait(0.128)
    sword.sever_artery(0.352)
    sword.gash(0.400)
    unarmed.swapWeapon(0.160)

    //LB 8
    longbow.fan_of_fire(0.560)
    longbow.scorched_earth(0.368)
    unarmed.outrage()
    longbow.arcing_arrow(0.560)
    longbow.dual_shot(0.832)
    longbow.dual_shot(0.848)
    longbow.dual_shot(0.880)
    longbow.scorched_earth(0.352)
    longbow.fan_of_fire(0.560)
    unarmed.swapWeapon(0.160)

    //Sword 8
    sword.savage_leap(1.008)
    torch.flames_of_war(0.512)
    torch.blaze_breaker(0.480)
    sword.final_thrust(0.449)
    unarmed.wait(0.079)
    sword.flaming_flurry(1.712)
    unarmed.wait(0.160)
    unarmed.outrage()
    unarmed.shattering_blow(0.528)
    unarmed.swapWeapon(0.160)

    //LB 9
    if (target.getDamageTaken < target.getHealth) longbow.fan_of_fire(0.560)
    if (target.getDamageTaken < target.getHealth) longbow.scorched_earth(0.352)
    if (target.getDamageTaken < target.getHealth) unarmed.blood_reckoning(0.320)
    if (target.getDamageTaken < target.getHealth) longbow.scorched_earth(0.368)
    if (target.getDamageTaken < target.getHealth) longbow.pin_down(0.672)
    if (target.getDamageTaken < target.getHealth) longbow.arcing_arrow(0.560)
    if (target.getDamageTaken < target.getHealth) unarmed.sundering_leap(1.040)
    if (target.getDamageTaken < target.getHealth) longbow.arcing_arrow(0.848)
    if (target.getDamageTaken < target.getHealth) longbow.scorched_earth(0.352)
    if (target.getDamageTaken < target.getHealth) longbow.fan_of_fire(0.401)
    if (target.getDamageTaken < target.getHealth) unarmed.swapWeapon(0.207)

    //Sword 9
    if (target.getDamageTaken < target.getHealth) sword.final_thrust(0.432)
    if (target.getDamageTaken < target.getHealth) unarmed.wait(0.480)
    if (target.getDamageTaken < target.getHealth) torch.blaze_breaker(0.528)
    if (target.getDamageTaken < target.getHealth) unarmed.outrage()
    if (target.getDamageTaken < target.getHealth) sword.flaming_flurry(1.840)

    //Padding
    if (target.getDamageTaken < target.getHealth) sword.savage_leap(1.008)
    if (target.getDamageTaken < target.getHealth) sword.sever_artery(0.368)
    if (target.getDamageTaken < target.getHealth) sword.gash(0.512)
    if (target.getDamageTaken < target.getHealth) unarmed.swapWeapon(0.200)
    if (target.getDamageTaken < target.getHealth) longbow.fan_of_fire(0.560)
    if (target.getDamageTaken < target.getHealth) longbow.scorched_earth(0.352)
    if (target.getDamageTaken < target.getHealth) longbow.arcing_arrow(0.560)
    if (target.getDamageTaken < target.getHealth) unarmed.shattering_blow(0.528)
    if (target.getDamageTaken < target.getHealth) longbow.dual_shot(0.832)
    if (target.getDamageTaken < target.getHealth) longbow.dual_shot(0.848)
    if (target.getDamageTaken < target.getHealth) longbow.scorched_earth(0.352)
    if (target.getDamageTaken < target.getHealth) longbow.fan_of_fire(0.401)
  }

  def sword_offhand_41_2k(target: Target): Unit = {
    sword.impale(0.032)
    sword.final_thrust(0.451)
    unarmed.swapWeapon(0.077)

    longbow.fan_of_fire(0.560)
    longbow.pin_down(0.672)
    unarmed.head_butt(1.008)
    unarmed.berserk()
    unarmed.outrage()
    longbow.scorched_earth(0.352)
    unarmed.blood_reckoning(0.320)
    longbow.scorched_earth(0.368)
    unarmed.shattering_blow(0.512)
    unarmed.sundering_leap(0.960)
    longbow.fan_of_fire(0.289)
    unarmed.swapWeapon(0.272)

    sword.savage_leap(1.008)
    sword.flaming_flurry(1.760)
    sword.final_thrust(0.432)
    sword.sever_artery(0.368)
    sword.gash(0.512)
    sword.hamstring(0.416)
    sword.impale(0.432)
    unarmed.swapWeapon(0.160)

    longbow.fan_of_fire(0.560)
    unarmed.outrage()
    longbow.scorched_earth(0.352)
    longbow.arcing_arrow(0.560)
    longbow.dual_shot(0.849)
    longbow.dual_shot(0.831)
    longbow.arcing_arrow(0.848)
    longbow.scorched_earth(0.432)
    longbow.fan_of_fire(0.561)
    unarmed.swapWeapon(0.240)

    sword.sever_artery(0.640)
    unarmed.shattering_blow(0.528)
    sword.final_thrust(0.432)
    unarmed.wait(0.080)
    unarmed.sundering_leap(0.960)
    sword.flaming_flurry(1.728)
    unarmed.outrage()
    sword.sever_artery(0.640)
    sword.impale(0.401)
    unarmed.swapWeapon(0.112)

    longbow.fan_of_fire(0.560)
    longbow.scorched_earth(0.368)
    unarmed.blood_reckoning(0.320)
    longbow.scorched_earth(0.352)
    longbow.pin_down(0.688)
    longbow.arcing_arrow(0.560)
    longbow.dual_shot(0.832)
    longbow.dual_shot(0.848)
    unarmed.wait(0.160)
    longbow.scorched_earth(0.352)
    longbow.fan_of_fire(0.369)
    unarmed.swapWeapon(0.192)

    unarmed.outrage()
    sword.savage_leap(1.008)
    sword.final_thrust(0.432)
    unarmed.wait(0.048)
    sword.flaming_flurry(1.792)
    unarmed.shattering_blow(0.528)
    sword.sever_artery(0.352)
    sword.gash(0.640)
    sword.impale(0.448)
    unarmed.swapWeapon(0.192)

    longbow.fan_of_fire(0.560)
    longbow.scorched_earth(0.368)
    longbow.arcing_arrow(0.560)
    unarmed.sundering_leap(0.992)
    longbow.arcing_arrow(0.560)
    unarmed.head_butt(0.960)
    unarmed.outrage()
    longbow.scorched_earth(0.448)
    longbow.fan_of_fire(0.481)
    unarmed.swapWeapon(0.160)

    sword.savage_leap(0.992)
    sword.final_thrust(0.448)
    unarmed.wait(0.080)
    sword.flaming_flurry(1.760)
    sword.sever_artery(0.352)
    sword.gash(0.528)
    sword.hamstring(0.400)
    sword.impale(0.480)
    unarmed.swapWeapon(0.112)

    longbow.fan_of_fire(0.560)
    longbow.scorched_earth(0.368)
    unarmed.blood_reckoning(0.272)
    longbow.scorched_earth(0.368)
    unarmed.outrage()
    longbow.pin_down(0.672)
    unarmed.shattering_blow(0.528)
    longbow.arcing_arrow(0.560)
    longbow.dual_shot(0.832)
    longbow.dual_shot(0.848)
    longbow.scorched_earth(0.368)
    longbow.fan_of_fire(0.320)
    unarmed.swapWeapon(0.224)

    unarmed.sundering_leap(0.960)
    sword.savage_leap(1.008)
    sword.flaming_flurry(1.712)
    unarmed.outrage()
    sword.final_thrust(0.448)
    unarmed.wait(0.080)
    sword.impale(0.432)
    sword.sever_artery(0.320)
    unarmed.swapWeapon(0.320)

    longbow.fan_of_fire(0.560)
    longbow.scorched_earth(0.368)
    longbow.arcing_arrow(0.560)
    longbow.dual_shot(0.832)
    longbow.dual_shot(0.848)
    longbow.arcing_arrow(0.640)
    unarmed.shattering_blow(0.512)
    longbow.scorched_earth(0.368)
    longbow.fan_of_fire(0.401)
    unarmed.swapWeapon(0.160)

    sword.savage_leap(0.992)
    unarmed.outrage()
    sword.flaming_flurry(1.728)
    sword.final_thrust(0.432)
    unarmed.wait(0.048)
    sword.sever_artery(0.352)
    sword.gash(0.528)
    sword.hamstring(0.400)
    sword.impale(0.512)
    unarmed.swapWeapon(0.080)

    longbow.fan_of_fire(0.560)
    longbow.scorched_earth(0.368)
    unarmed.blood_reckoning(0.320)
    longbow.scorched_earth(0.352)
    longbow.pin_down(0.688)
    longbow.arcing_arrow(0.560)
    unarmed.sundering_leap(1.280)
    longbow.arcing_arrow(0.592)
    longbow.scorched_earth(0.448)
    unarmed.outrage()
    longbow.fan_of_fire(0.401)
    unarmed.swapWeapon(0.160)

    sword.savage_leap(0.992)
    unarmed.shattering_blow(0.528)
    sword.final_thrust(0.432)
    unarmed.wait(0.080)
    sword.flaming_flurry(1.920)
    sword.impale(0.448)
    sword.sever_artery(0.352)
    sword.gash(0.369)
    unarmed.swapWeapon(0.192)

    longbow.fan_of_fire(0.560)
    longbow.scorched_earth(0.368)
    longbow.arcing_arrow(0.560)
    unarmed.head_butt(0.960)
    unarmed.outrage()
    longbow.dual_shot(0.832)
    longbow.dual_shot(0.848)
    longbow.scorched_earth(0.352)
    longbow.fan_of_fire(0.529)
    unarmed.swapWeapon(0.272)

    unarmed.sundering_leap(0.960)
    sword.savage_leap(1.008)
    sword.final_thrust(0.448)
    unarmed.wait(0.064)
    sword.flaming_flurry(1.808)
    unarmed.shattering_blow(0.512)
    sword.impale(0.448)
    unarmed.swapWeapon(0.080)

    unarmed.outrage()
    longbow.fan_of_fire(0.560)
    longbow.scorched_earth(0.352)
    unarmed.blood_reckoning(0.288)
    longbow.scorched_earth(0.352)
    longbow.pin_down(0.688)
    longbow.arcing_arrow(0.560)
    longbow.dual_shot(0.832)
    longbow.dual_shot(0.848)
    unarmed.wait(0.240)
    longbow.scorched_earth(0.352)
    longbow.fan_of_fire(0.417)
    unarmed.swapWeapon(0.144)

    sword.final_thrust(0.448)
    unarmed.wait(0.160)
    sword.flaming_flurry(1.760)
    sword.savage_leap(1.1120)
    unarmed.outrage()
    sword.sever_artery(0.352)
    sword.gash(0.528)
    sword.hamstring(0.400)
    if (target.getDamageTaken < target.getHealth) sword.impale(0.448)
    if (target.getDamageTaken < target.getHealth) unarmed.swapWeapon(0.160)

    if (target.getDamageTaken < target.getHealth) longbow.fan_of_fire(0.560)
    if (target.getDamageTaken < target.getHealth) longbow.scorched_earth(0.368)
    if (target.getDamageTaken < target.getHealth) longbow.arcing_arrow(0.560)
    if (target.getDamageTaken < target.getHealth) unarmed.shattering_blow(0.512)
    if (target.getDamageTaken < target.getHealth) unarmed.sundering_leap(0.960)
    if (target.getDamageTaken < target.getHealth) longbow.arcing_arrow(0.560)
    if (target.getDamageTaken < target.getHealth) longbow.scorched_earth(0.368)
    if (target.getDamageTaken < target.getHealth) longbow.fan_of_fire(0.401)
  }

}