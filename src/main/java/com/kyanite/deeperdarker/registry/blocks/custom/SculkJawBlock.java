package com.kyanite.deeperdarker.registry.blocks.custom;

import com.kyanite.deeperdarker.miscellaneous.DDTypes;
import com.kyanite.deeperdarker.registry.blocks.DDBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SculkJawBlock extends Block {
    public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");
    public DamageSource damageSource = new DamageSource("jaw");

    public SculkJawBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(ACTIVATED, false));
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        super.tick(pState, pLevel, pPos, pRandom);
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if(!pState.is(DDBlocks.SCULK_JAW.get())) return;
        if(pState.getValue(ACTIVATED)) return;

        if(pEntity instanceof Player plr) {
            if(plr.isCreative() || plr.isSpectator() || plr.isCrouching()) return;
        }

        if(pEntity instanceof LivingEntity mob) {
            if(mob.getMobType().equals(DDTypes.SCULK)) return;

            mob.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 80));
            mob.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 80, 1));
        }

        pEntity.hurt(damageSource, 4); // They take damage even if they don't fall in
        pEntity.setDeltaMovement(Vec3.ZERO);
        pLevel.setBlock(pPos, DDBlocks.SCULK_JAW.get().defaultBlockState().setValue(ACTIVATED, true), 3);
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if(!pState.is(DDBlocks.SCULK_JAW.get())) return;

        if(pEntity instanceof ItemEntity itemEntity) {
            itemEntity.remove(Entity.RemovalReason.KILLED);
            return;
        }

        if(pEntity instanceof Player plr) {
            plr.giveExperiencePoints(-1);
        }

        pEntity.hurt(damageSource, 3);
    }

    @Override
    public boolean isValidSpawn(BlockState state, BlockGetter level, BlockPos pos, SpawnPlacements.Type type, EntityType<?> entityType) {
        if(!isBlockBeside(level, this, pos)) return true;
        return super.isValidSpawn(state, level, pos, type, entityType);
    }

    public boolean isBlockBeside(BlockGetter level, Block targetBlock, BlockPos origin) {
        return level.getBlockState(origin.north()).is(targetBlock) || level.getBlockState(origin.east()).is(targetBlock) || level.getBlockState(origin.south()).is(targetBlock) || level.getBlockState(origin.west()).is(targetBlock);
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if(!pState.is(DDBlocks.SCULK_JAW.get())) return;
        if(!pState.getValue(ACTIVATED)) return;

        pLevel.setBlock(pPos, pState.setValue(ACTIVATED, false), 3);
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if(!pState.is(DDBlocks.SCULK_JAW.get())) return super.getCollisionShape(pState, pLevel, pPos, pContext);

        if(pState.getValue(ACTIVATED)) return Block.box(0, 0, 0, 0, 0, 0);

        return super.getCollisionShape(pState, pLevel, pPos, pContext);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(ACTIVATED);
    }
}
